package com.example.logging.aspect;

import com.example.logging.model.LogEntry;
import com.example.logging.mapper.LogEntryMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final LogEntryMapper logEntryMapper;

    @Autowired
    public LoggingAspect(LogEntryMapper logEntryMapper) {
        this.logEntryMapper = logEntryMapper;
    }

    @Pointcut("@annotation(com.example.logging.annotation.Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("Method {} is called in class {}", methodName, className);
        LogEntry logEntry = new LogEntry();
        logEntry.setTimestamp(System.currentTimeMillis());
        logEntry.setLevel("INFO");
        logEntry.setClassName(className);
        logEntry.setMethodName(methodName);
        logEntry.setMessage("Method " + methodName + " is called in class " + className);
        logEntryMapper.insert(logEntry);
    }

    @AfterThrowing(pointcut = "loggableMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.error("Exception occurred in method {} in class {}: {}", methodName, className, exception.getMessage());
        LogEntry logEntry = new LogEntry();
        logEntry.setTimestamp(System.currentTimeMillis());
        logEntry.setLevel("ERROR");
        logEntry.setClassName(className);
        logEntry.setMethodName(methodName);
        logEntry.setMessage("Exception occurred in method " + methodName + " in class " + className + ": " + exception.getMessage());
        logEntryMapper.insert(logEntry);
    }
}
