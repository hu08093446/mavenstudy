package learnaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Around("execution(* learnaspect.mypac.FuncPac.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Aspect before my func...");
        Object object = pjp.proceed();

        Object[] args = pjp.getArgs();
        //这样可以拿到具体的参数值，还是很666的
        for(Object arg : args) {
            System.out.println(arg.getClass().getSimpleName());
            System.out.println(arg.toString());
        }


        //切面涉及了具体的类型，这样不太好
//        Weather wnew = new Weather(null, false);
//        object = wnew;

        System.out.println("Aspect after my func...");

        return object;
    }
}
