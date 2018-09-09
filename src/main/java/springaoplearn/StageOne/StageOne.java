package springaoplearn.StageOne;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import springaoplearn.*;

public class StageOne {
    public static void main(String[] args) {
        //1.初始化源对象(一定要实现接口)
        UserService target =new UserServiceImpl();
        //2.AOP 代理工厂
        ProxyFactory pf = new ProxyFactory(target);
        //3.装配Advice
        pf.addAdvice(new SecurityInterceptor());
        pf.addAdvice(new SplitAfterMethod());
        pf.addAdvisor(new DefaultPointcutAdvisor(new LoggerBeforeAdvice()));

        ////4.获取代理对象
        UserService proxy =(UserService)pf.getProxy();

        //5.调用业务
        proxy.updateUser();
        proxy.queryUser();
    }
}
