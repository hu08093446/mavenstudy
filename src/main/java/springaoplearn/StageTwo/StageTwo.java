package springaoplearn.StageTwo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springaoplearn.UserService;

public class StageTwo {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springaoplearn/StageTwo/proxy.xml");

        UserService userService = ctx.getBean("userService", UserService.class);
        userService.updateUser();
    }
}
