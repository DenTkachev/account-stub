package ru.iteco.account.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ThrowCheckAspect {

    @Around("allMethodInRepo()")
    public Object aroundAllMethodThrow(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("aroundAllMethodThrow: START METHOD {}", joinPoint.getSignature().toShortString());

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Exception proceed! Return NULL!", e);
            return null;
        } finally {
           log.info("aroundAllMethodThrow: END METHOD: {}", joinPoint.getSignature().toShortString());
        }
    }

    @Pointcut("within(ru.iteco.account.aop..*)")
    public void allMethodInRepo() {}

}
