package com.pomina.commonservices.user_activity.controller;

import com.pomina.commonservices.user_activity.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserActivityController {

    private final UserActivityService userActivityService;
}
