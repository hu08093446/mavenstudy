package springaoplearn;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class SplitAfterMethod implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("---------------------------------------华丽丽的分割线----------------------------------------");
    }
}
