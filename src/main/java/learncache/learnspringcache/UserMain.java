package learncache.learnspringcache;

import learncache.Person;
import learncache.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("learnspringcache/app.xml");
        UserService userService = (UserService) context.getBean("userService");

        testGetUser(userService);
        testGetPerson(userService);
    }

    private static void testGetUser(UserService userService) {
        User user = null;
        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0602");
        System.out.println(user);

        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0602");
        System.out.println(user);

        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0601");
        System.out.println(user);

        user = userService.getUserById("0603");
        System.out.println(user);
        user = userService.getUserById("0603");
        System.out.println(user);
        user = userService.getUserById("0601");
        System.out.println(user);

        user = userService.getUserById("1601");
        System.out.println(user);
        user = userService.getUserById("1601");
        System.out.println(user);
    }

    private static void testGetPerson(UserService userService) {
        Person person = null;
        person = userService.getPerson("1601");
        System.out.println(person);
        person = userService.getPerson("1601");
        System.out.println(person);
    }
}
