package learncache;

public class UserMain {
    public static void main(String[] args) {
        UserService userService = new UserService();

        User user = null;
        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0602");
        System.out.println(user);

        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0602");
        System.out.println(user);

        userService.reload();
        System.out.println("after cache reload......");
        user = userService.getUserById("0601");
        System.out.println(user);
        user = userService.getUserById("0601");
        System.out.println(user);
    }
}
