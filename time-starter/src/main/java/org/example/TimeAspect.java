package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@Aspect
public class TimeAspect {

    @Pointcut("within(@org.example.Time *)")
    public void beansMethod(){

    }

    @Pointcut("@annotation(org.example.Time)")
    public void beansWithAnnotation(){

    }

    @Around("beansMethod() || beansWithAnnotation()")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable{

        System.out.println("className: " + joinPoint.getTarget().getClass().getSimpleName());
        System.out.println("methodName: " + joinPoint.getSignature().getName());


        try {

            long startTime = LocalTime.now().toSecondOfDay();
            Object result = joinPoint.proceed();
            long stopTime = LocalTime.now().toSecondOfDay();
            System.out.println("время исполнения: " + (stopTime - startTime) + " секунд");

            return result;

        }catch (Throwable exception){

            System.out.println(exception.getMessage());
            return null;
        }

    }
}
