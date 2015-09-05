package example.aop;

import example.annotation.ReplacingMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class ReplacingAspect {
    @Pointcut("@annotation(example.annotation.ReplacingMethod)")
    public void methodToBeReplaced() {
    }

    @Around("methodToBeReplaced()")
    public void doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        ReplacingMethod replacingMethodAnnotation = method.getAnnotation(ReplacingMethod.class);
        Method methodToCall = pjp.getTarget().getClass().getMethod(replacingMethodAnnotation.value());
        methodToCall.invoke(pjp.getTarget());
    }
}
