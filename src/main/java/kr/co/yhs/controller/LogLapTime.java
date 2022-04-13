package kr.co.yhs.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LogLapTime {
//    @Around("execution(* kr.co.yhs.controller.*(..))")
    public Object lapTimeCalc(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object o = pjp.proceed();
        sw.stop();
        long executionTime = sw.getTotalTimeMillis();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String task = className + "." + methodName;
        log.debug("[ExecutionTime] " + task + "-->" + executionTime + "(ms)");
        return o;

    }
}
