package basiclearn;



import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("usePostConstruct")
public class UsePostConstruct implements InitializingBean {

    public UsePostConstruct() {
        System.out.println("-------------------construct");
    }

    // 需要把类做成bean交给spring管理，spring会使该注解起作用
    // 执行顺序：构造器-->自动注入-->PostConstrut-->InitializingBean(afterPro...方法)->xml中配置init方法
    @PostConstruct
    public void init() {
        System.out.println("------------------init");
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
