package com.pomina.commonservices.user_activity.anotation;

import com.pomina.commonservices.user_activity.service.UserActivityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class UserActionAspect {

    private final UserActivityService userActivityService;

    @Around("@annotation(userAction)")
    public Object logUserAction(ProceedingJoinPoint joinPoint, UserAction userAction) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // Lấy value từ annotation
        String actionName = userAction.value();

        // Gọi service để log
        userActivityService.logUserAction(request, actionName);

        return joinPoint.proceed();
    }
}
