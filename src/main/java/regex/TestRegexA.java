package regex;

import com.alibaba.fastjson.JSON;
import model.BaseResult;
import model.BaseResultInt;

import java.lang.reflect.Field;

public class TestRegexA {

    private static BaseResultInt funA() {
        BaseResultInt result = new BaseResultInt();
        result.setRetCode(201);
        result.setRetDesc("yyf fail.");
//        System.out.println(result);

        return result;

    }

    public static void main(String[] args) {

        String str1 = "retCode";
        String str2 = "200";

        Object result = funA();
//        System.out.println(result.getClass().getCanonicalName());

//        Class resultClass = ClassLoader.getSystemClassLoader().loadClass(result.getClass().getCanonicalName());
        boolean flag = false;
        Field[] fields = result.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            if(!varName.equals(str1)) {
                continue;
            }
            try {
                boolean access = field.isAccessible();
                if (!access) {
                    field.setAccessible(true);
                }

                Object o = field.get(result);
//                System.out.println(o.getClass().getCanonicalName());
                System.out.println(varName + "=" + o);

                if(o.toString().equals(str2)) {
                    flag = true;
                }

                if (!access) {
                    field.setAccessible(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        System.out.println(flag);
    }
}
