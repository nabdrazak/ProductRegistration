package com.lautbiru.productRegistration.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("************ 1 - Pre Handler");
        logger.info("************ 1 - Pre Handler : Method Type --->  "+request.getMethod());
        logger.info("************ 1 - Pre Handler : Request URI --->  "+request.getRequestURI());
        logger.info("************ 1 - Pre Handler : Servlet Path --->  "+request.getServletPath());

        logger.info("************ 1 - Pre Handler : Servlet Path ---> " +
                "FUTURE : We can set some validation to validate request and return false if the request not correct");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("************ 2 - Post Handler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("************ 3 - After Completion");
        if(ex != null) {
            logger.info("************ 3 - After Completion  :  --->  "+ex.getMessage());
        }
    }
}
