package com.pomina.commonservices.user_activity.interceptor;

import com.pomina.commonservices.user_activity.anotation.UserAction;
import com.pomina.commonservices.user_activity.model.dto.request.UserActionRequest;
import com.pomina.commonservices.user_activity.service.UserActivityService;
import com.pomina.commonservices.user_activity.utils.RequestUtils;
import com.pomina.security.config.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class UserActivityInterceptor implements HandlerInterceptor {

    private final UserActivityService userActivityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        // Trích xuất sessionId trong preHandle
        String sessionId = request.getSession(false) != null ? request.getSession().getId() : "NOT_SESSION";
        request.setAttribute("sessionId", sessionId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        String actionName = "Hành động chưa được định nghĩa!";

        if (handler instanceof HandlerMethod handlerMethod) {
            UserAction userAction = handlerMethod.getMethodAnnotation(UserAction.class);
            if (userAction != null && !userAction.actionName().isEmpty()) {
                actionName = userAction.actionName();
            }
        }

        // Gọi service để log
        userActivityService.logUserAction(
                UserActionRequest.builder()
                        .userId(userPrincipal.getUserId())
                        .username(userPrincipal.getUsername())
                        .sessionId((String) request.getAttribute("sessionId"))
                        .action(actionName)
                        .requestUrl(request.getRequestURI())
                        .requestMethod(request.getMethod())
                        .requestParams(RequestUtils.extractRequestParams(request))
                        .build()
        );
    }
}
