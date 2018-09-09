package learnexception;

import learnexception.domain.MyException;

/**
 * 抛异常动作之后所有的代码都不在执行
 * @author Administrator
 */
public class ExceptionCloseAllOtherCode {
    public static void main(String[] args) {
//        try {
//            throw new MyException("producing my excetpion");
//            System.out.println("this code will do ?");
//        } catch (Exception e) {
//            System.out.println("catching exception...");
//        }

        try {
            throwException();
            System.out.println("this code will do ?");
        } catch (Exception e) {
            System.out.println("catching exception...");
        }
    }

    private static void throwException() {
        throw new MyException("producing my excetpion");
    }
}
