package transaction.program;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("db-jpa.xml");
        WatchingDouyu douyu = applicationContext.getBean(WatchingDouyu.class);
        douyu.serviceA();

//        WatchingLengLeng lengLeng = applicationContext.getBean(WatchingLengLeng.class);
//        lengLeng.serviceB();

    }


}
