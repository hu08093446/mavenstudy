package springaoplearn;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LoggerBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("=======保存更新日志=========");
    }
}
