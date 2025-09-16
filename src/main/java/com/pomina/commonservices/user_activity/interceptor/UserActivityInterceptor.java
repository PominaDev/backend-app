package com.pomina.commonservices.user_activity.interceptor;

import com.pomina.commonservices.user_activity.anotation.UserAction;
import com.pomina.commonservices.user_activity.service.UserActivityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
@RequiredArgsConstructor
public class UserActivityInterceptor implements AsyncHandlerInterceptor {

    private final UserActivityService userActivityService;

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {

        String actionName = "Không xác định!";

        if (handler instanceof HandlerMethod handlerMethod) {
            UserAction userAction = handlerMethod.getMethodAnnotation(UserAction.class);
            if (userAction != null) {
                actionName = userAction.value();
            }
        }

        userActivityService.logUserAction(request, actionName);
    }
}
