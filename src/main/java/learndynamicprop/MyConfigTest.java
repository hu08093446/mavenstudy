package learndynamicprop;

import idgenerator.util.SpringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class MyConfigTest {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:learndynamicprop/prop.xml");

//        PropertyPlaceholderConfigurer placeholderConfigurer =
//                SpringUtils.getBean("propertyConfigurer");

        MyPropertyConfigurer configurer = SpringUtils.getBean("propertyConfigurer");

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(PropertyUtils.getString("name"));
        }

    }
}
