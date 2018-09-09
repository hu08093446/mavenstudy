package learnproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        UserManager target = new UserManagerImpl();

        UserManager proxy = (UserManager) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new UserManagerProxy(target));
        proxy.addUser();
    }

}


interface UserManager {

    public void addUser();
}

class UserManagerImpl implements UserManager {

    @Override
    public void addUser() {
        System.out.println("add user...");
    }

}

class UserManagerProxy implements InvocationHandler {

    private UserManager target;

    public UserManagerProxy(UserManager target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        System.out.println("test");
        // 这里的proxy对应UserManagerProxy，所以打印proxy是不可以的，会导致递归调用，引发栈溢出
        System.out.println("check privilege " + proxy);

        method.invoke(target, args);

        return null;
    }

}