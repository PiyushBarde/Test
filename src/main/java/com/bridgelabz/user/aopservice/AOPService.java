package com.bridgelabz.user.aopservice;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AOPService {
    private static final Logger log = LoggerFactory.getLogger(AOPService.class);

    public AOPService() {
    }

    @Before("execution(* com.bridgelabz.user.controller.UserController.*(..))")
    public void logBeforeV1(JoinPoint joinPoint) {
        log.info("Initiating API : " + joinPoint.getSignature().getName() + " ");
    }

    @After("execution(* com.bridgelabz.user.controller.UserController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("API successfully Executed : " + joinPoint.getSignature().getName() + " ");
    }

    @AfterReturning("execution(* com.bridgelabz.user.service.UserService.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        log.info("User returned to controller : " + joinPoint.getSignature().getName() + " ");
    }

    @After("execution(* com.bridgelabz.user.controller.UserController.*(..))")
    public void logAfterAndSaveArgs(JoinPoint joinPoint) {
        log.info("After : " + Arrays.toString(joinPoint.getArgs()));
    }
}

