package learnexception;

import learnexception.domain.MyException;
import learnexception.domain.TestRsp;

import java.util.Random;

public class MyExceptionTest {

    public static void main(String[] args) {
        MyExceptionTest exceptionTest = new MyExceptionTest();

        for(int i = 0; i < 20; i++) {
            TestRsp rsp = exceptionTest.testMain();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(rsp.getErrorCode() + " " + rsp.getErrorMsg());

            System.out.println("----------------------------------------------------");
        }

        Integer x = 4;
        testInteger(x);
        System.out.println(x);
    }

    public static void testInteger(Integer x) {
        x = x * 3;
        System.out.println(x);
    }

    private TestRsp testMain() {

        TestRsp rsp = new TestRsp();
        try {
            rsp = testFunA();
        } catch (MyException e) {
            System.out.println("we encounter som difficulties during the competition");

            rsp.setErrorMsg(e.getMessage());
            rsp.setErrorCode(e.getErrorCode());
        }

        return rsp;
    }

    private TestRsp testFunA() {
        Random random = new Random();
        int min = 1;
        int max = 10;

        TestRsp rsp = new TestRsp();
//        String[] sexs = {"men", "women", "neutral"};
//        String excepitonSex = "neutral";
//
//        for(String sex : sexs) {
//            if(Objects.equals(excepitonSex, sex)) {
//                throw new MyException("Dota2.0000", "fuck you");
//            } else {
//                System.out.println(sex);
//            }
//        }

        testFunOne(random, min, max);
        testFunTwo(random, min, max);
        testFunThird(random, min, max);
        testFunFour(random, min, max);

        rsp.setErrorCode("Dota2.0000");
        rsp.setErrorMsg("we get all the ranking");
        return rsp;
    }

    private void testFunOne(Random random, int min, int max) {
        int key = 1;
        if(key == (random.nextInt(max - min + 1) + min)) {
            throw new MyException("Dota2.0301", "we can not get first");
        }
        System.out.println("we get first");
    }

    private void testFunTwo(Random random, int min, int max) {
        int key = 2;
        if(key == (random.nextInt(max - min + 1) + min)) {
            throw new MyException("Dota2.0302", "we even can not get second");
        }
        System.out.println("we get second");
    }

    private void testFunThird(Random random, int min, int max) {
        int key = 3;
        if(key == (random.nextInt(max - min + 1) + min)) {
            throw new MyException("Dota2.0303", "we can not get third");
        }
        System.out.println("we get third");
    }

    private void testFunFour(Random random, int min, int max) {
        int key = 4;
        if(key == (random.nextInt(max - min + 1) + min)) {
            throw new MyException("Dota2.0304", "we can not get fourth");
        }
        System.out.println("we get fourth");
    }



}
