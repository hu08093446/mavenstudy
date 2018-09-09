package learnaspect;

import learnaspect.mypac.FuncPac;
import learnaspect.mypac.Weather;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aspect.xml");
        FuncPac pac = context.getBean(FuncPac.class);

        Date date = new Date();

        boolean flag = false;

        Weather result = pac.funcA(date, flag);

        System.out.println(result);

        //ani声明为animal类型还是Weather类型都是不可以的
        //声明为Weather类型，则在编译时无法通过
        //声明为animal类型，那么在切面返回时（返回的是Weather对象），类型转换无法通过
        //所以在切面最好不要涉及返回类型的修改（个人愚见）
//        Weather ani = pac.funcB("cat", true);
//        Animal aniA = pac.funcB("cat", true);

//        System.out.println(aniA);
    }
}
