package com.chan.config;

import com.chan.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bronchan
 * @ClassName LoginInterceptor
 * @date 2021/8/6 20:04
 * @Version 1.0
 * @Description TODO
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getSession().setAttribute("message","请先登陆");
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
