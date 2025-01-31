package com.itsc.patientManagement.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.patientmanagement.service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.patientmanagement.service.*.*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        System.out.println("Method executed: " + joinPoint.getSignature().getName() + ", Result: " + result);
    }
}
