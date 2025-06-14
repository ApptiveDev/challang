package com.challang.backend.user.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.exception.UserErrorCode;
import com.challang.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public User getByLoginId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BaseException(UserErrorCode.USER_NOT_FOUND));
    }
}
