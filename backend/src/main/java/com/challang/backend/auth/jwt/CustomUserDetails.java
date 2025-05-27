package com.challang.backend.auth.jwt;


import com.challang.backend.user.entity.*;
import java.util.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;


    public Long getUserId() {
        return user.getUserId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getOauthId() {
        return user.getOauthId();
    }

    public AuthType getAuthType() {
        return user.getAuthType();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().getAuthority());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

    }

    @Override
    public String getUsername() {
        if (user.getAuthType() == AuthType.EMAIL) {
            return user.getEmail();
        } else {
            return user.getOauthId();
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


}
