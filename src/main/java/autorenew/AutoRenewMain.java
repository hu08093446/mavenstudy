package autorenew;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoRenewMain {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("autorenew/autorenew.xml");

    public static void main(String[] args) {
        String type = "four";
        ComplexParam param = new ComplexParam();
        testHandler(null, param);

        System.out.println("------------------------------------------------------------" + param);
    }

    private static void testHandler(String type, ComplexParam param) {
        HandlerManagerFactory.getHandlerManager(type).handle(param);
    }
}
