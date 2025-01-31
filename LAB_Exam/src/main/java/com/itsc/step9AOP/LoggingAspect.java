//TASK-9

package com.itsc.step9AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Before advice for all methods in BookRegistrationServlet
    @Before("execution(* com.itsc.OnlineBookstore.BookRegistrationServlet.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature());
    }

    // After returning advice for successful execution
    @AfterReturning(pointcut = "execution(* com.itsc.OnlineBookstore.BookRegistrationServlet.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Successfully executed: " + joinPoint.getSignature());
    }

    // After throwing advice for exceptions
    @AfterThrowing(pointcut = "execution(* com.itsc.OnlineBookstore.BookRegistrationServlet.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Exception in: " + joinPoint.getSignature());
        System.out.println("Error: " + error.getMessage());
    }
}


