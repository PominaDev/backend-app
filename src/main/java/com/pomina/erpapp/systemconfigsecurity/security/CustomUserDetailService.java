package com.pomina.erpapp.systemconfigsecurity.security;

import com.pomina.erpapp.systemconfigsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.opentelemetry.api.internal.ApiUsageLogger.log;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userService.findByUserName(userName)
                .orElseThrow();

        log("[DEBUG] user.password: " + user.getPassword());
        log("[DEBUG] matches = " + passwordEncoder.matches("123", user.getPassword()));

        return UserPrincipal.builder()
                .sysUser(user)
                .userId(user.getUserId())
                .userName(user.getUsername())
                .hoVaTen(user.getHoVaTen())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRoleName())))
                .build()
                ;
    }
}
