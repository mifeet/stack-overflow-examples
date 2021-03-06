package com.example.aop;

import com.example.annotation.ReplacingMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

// aspect is a module encapsulating your replacing functionality
@Aspect
public class ReplacingAspect {
    // pointcut gives an expression selecting the "joint points" to be intercepted
    @Pointcut("@annotation(com.example.annotation.ReplacingMethod)")
    public void methodToBeReplaced() {
    }

    // advice defining the code executed at joint points selected by given pointcut;
    // in our case @Around is executed instead of the method call selected by pointcut methodToBeReplaced()
    @Around("methodToBeReplaced()")
    public void replaceMethodCall(ProceedingJoinPoint pjp) throws Throwable {
        // get reference to the method to be replaced
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        // extract the name of the method to be called from ReplacingMethod annotation
        ReplacingMethod replacingMethodAnnotation = method.getAnnotation(ReplacingMethod.class);
        String methodToCallName = replacingMethodAnnotation.value();
        // use reflection to call the method
        Method methodToCall = pjp.getTarget().getClass().getMethod(methodToCallName);
        methodToCall.invoke(pjp.getTarget());
    }
}
