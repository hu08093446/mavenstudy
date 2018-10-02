package learnspringaop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("learnspringaop/springaop.xml");

        MockTask task = (MockTask) context.getBean("taskProxy");
        task.execute();
    }
}
