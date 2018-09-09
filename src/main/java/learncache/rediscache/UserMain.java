package learncache.rediscache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("learnspringcache/rediscache/redisapp.xml");
        CommonService service = context.getBean(CommonService.class);
        System.out.println(service.queryUser("0601"));
        System.out.println(service.queryUser("0601"));
        System.out.println(service.queryUser("0603"));
        System.out.println(service.queryUser("0603"));
        System.out.println(service.queryUser("0604"));
    }
}
