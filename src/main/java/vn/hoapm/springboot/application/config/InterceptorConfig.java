package vn.hoapm.springboot.application.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InterceptorConfig extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) {
        HttpSession httpSession = requestServlet.getSession();
        httpSession.setAttribute("lang", requestServlet.getHeader("lang"));
        httpSession.setAttribute("currentUserId", requestServlet.getHeader("currentUserId"));
        httpSession.setAttribute("acceptLanguage", requestServlet.getHeader("Accept-Language"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) {
    }

}
