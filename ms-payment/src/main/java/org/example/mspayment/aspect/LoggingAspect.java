package org.example.mspayment.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* org.example.mspayment.controller..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(
                "ActonLog.{}() with arguments[s] = {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs()
        );
        try{
            var result = joinPoint.proceed();

            log.info(
                    "ActonLog.{}() with arguments[s] = {}",
                    joinPoint.getSignature().getName(),
                    result
            );
            return result;
        } catch(IllegalArgumentException e){
            log.error(
                    "ActonLog.{}() with arguments[s] = {}",
                    joinPoint.getSignature().getName(),
                    joinPoint.getArgs()

            );
            throw e;
        }

    }
}
