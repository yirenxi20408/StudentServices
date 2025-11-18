package com.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Logging aspect for student service operations
 */
@Aspect
@Component
public class LoggingAspect {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Pointcut for all methods in StudentService
     */
    @Pointcut("execution(* com.student.service.StudentService.*(..))")
    public void studentServiceMethods() {
    }

    /**
     * Before advice - logs before method execution
     */
    @Before("studentServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String timestamp = dateFormat.format(new Date());
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        System.out.println("=== AOP日志 (AOP Log) ===");
        System.out.println("时间 (Time): " + timestamp);
        System.out.println("操作 (Operation): " + methodName);
        System.out.println("参数 (Parameters): " + Arrays.toString(args));
    }

    /**
     * After returning advice - logs after successful method execution
     */
    @AfterReturning(pointcut = "studentServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("操作 (Operation) '" + methodName + "' 执行成功 (executed successfully)");
        if (result != null) {
            System.out.println("返回值 (Return value): " + result);
        }
        System.out.println("======================\n");
    }

    /**
     * After throwing advice - logs when method throws an exception
     */
    @AfterThrowing(pointcut = "studentServiceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("操作 (Operation) '" + methodName + "' 执行失败 (execution failed)");
        System.out.println("错误信息 (Error message): " + exception.getMessage());
        System.out.println("======================\n");
    }

    /**
     * Around advice - logs execution time
     */
    @Around("studentServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("执行时间 (Execution time): " + executionTime + "ms");
        }
        
        return result;
    }
}
