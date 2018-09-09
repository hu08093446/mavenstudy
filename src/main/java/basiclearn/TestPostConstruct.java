package basiclearn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPostConstruct {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("basiclearn/basic.xml");
    }
}
