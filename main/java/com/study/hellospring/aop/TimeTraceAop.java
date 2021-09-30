package com.study.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    //@Around 는 위치를 지정해주는것인데 하위폴더를 다 지정해줌
    @Around("execution(* com.study.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            //alt +Enter 로 inline 해서 간결하게 만들 수 있는데
            //그럼 내가 이해를 못할까봐 안해놨음
            Object result = joinPoint.proceed();
            return result;
        } finally {
            Long finish = System.currentTimeMillis();
            Long timeMs = finish - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
