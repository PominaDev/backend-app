package com.pomina.erpapp.systemconfigsecurity.sysservice;

import com.pomina.erpapp.systemconfigsecurity.security.JwtIssuer;
import com.pomina.erpapp.systemconfigsecurity.security.UserPrincipal;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String userName, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(userPrincipal.getUserId())
                .userName(userPrincipal.getUsername())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build());
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}

