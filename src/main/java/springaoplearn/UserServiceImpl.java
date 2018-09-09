package springaoplearn;

public class UserServiceImpl implements UserService{

    @Override
    public void updateUser() {
        System.out.println("$$$$$$执行业务逻辑$$$$$");
    }

    @Override
    public void queryUser() {
        System.out.println("----------query user---------------");
    }
}
