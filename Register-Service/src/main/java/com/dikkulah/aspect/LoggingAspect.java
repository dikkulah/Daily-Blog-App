package com.dikkulah.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.dikkulah.business.services.UserService.createUser())")
    public void getAllAdvice() {
        log.info("create user starting");
    }
}
