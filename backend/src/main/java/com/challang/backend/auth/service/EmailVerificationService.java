package com.challang.backend.auth.service;

import com.challang.backend.auth.exception.AuthErrorCode;
import com.challang.backend.global.exception.BaseException;
import com.challang.backend.global.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private static final long EXPIRE_SECONDS = 60 * 5L; // 5분
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;
    private final Random random = new Random();


    // 랜덤 6자리 숫자 redis에 저장하고 반환
    public String generateAuthNumber(String email) {
        String authNumber = IntStream.range(0, 6)
                .map(i -> random.nextInt(10))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        redisUtil.setDataExpire(authNumber, email, EXPIRE_SECONDS);
        return authNumber;
    }

    // 이메일로 인증번호 전송
    public void sendAuthEmail(String email) {
        String authNumber = generateAuthNumber(email);

        String title = "인증 이메일입니다.";
        String content = "찰랑을 방문해주셔서 감사합니다."
                + "<br><br>"
                + "인증 번호는 "
                + authNumber
                + "입니다."
                + "<br>인증번호를 제대로 입력해주세요";

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("dionisos198@naver.com");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BaseException(AuthErrorCode.MAIL_SEND_FAILED);
        }
    }

    // redis에 저장된 번호와 매칭
    public boolean verifyAuthNumber(String email, String authNumber) {
        String storedEmail = redisUtil.getData(authNumber);
        if (storedEmail == null) {
            return false;
        }

        if (!storedEmail.equals(email)) {
            return false;
        }

        // 인증 성공 시 redis에서 삭제
        redisUtil.deleteData(authNumber);

        return true;
    }

}
