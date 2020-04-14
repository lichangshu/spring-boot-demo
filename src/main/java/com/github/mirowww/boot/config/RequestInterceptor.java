package com.github.mirowww.boot.config;

import javax.servlet.http.HttpServletRequest;

import com.github.mirowww.boot.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.github.mirowww.boot.controller.BasicController;

@Aspect
@Configuration
public class RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Pointcut("execution(* com.xy.sts.street.controller.*.*(..))")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {
    }

    @Around("controller() && (postMapping() || deleteMapping() || requestMapping())")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            return point.proceed(point.getArgs());
        } finally {
            if (request != null) {
                if (!"GET".equals(request.getMethod())) {
                    User user = BasicController.local.get();
                    if (user != null) {
                        String uri = request.getRequestURI();
                        log.info("User [{}], {}, Args : {}", user.getOpenid(), uri, point.getArgs());
                    }
                }
            }

        }
    }
}
