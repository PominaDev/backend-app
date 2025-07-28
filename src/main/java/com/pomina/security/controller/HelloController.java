package com.pomina.security.controller;

import com.pomina.security.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "If you see this, then you are logged in as user " + userPrincipal.getUserId()
                + "-userName:" + userPrincipal.getUsername()
                ;
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "If you see this, then you are an Admin " + userPrincipal.getUserId()
                + "-userName:" + userPrincipal.getUsername()
                ;
    }
}
