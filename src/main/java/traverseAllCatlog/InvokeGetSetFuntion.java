package traverseAllCatlog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 调用一个实体类中所有的get和set方法
 */
public class InvokeGetSetFuntion {
    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {

        invokeGetSet("learncache.Person");
    }

    /**
     * 根据类的包路径获取对象并调用方法
     * @param classPackagePath 类的包路径
     */
    public static void invokeGetSet(String classPackagePath)
            throws ClassNotFoundException, InstantiationException, InvocationTargetException {
        Object object;
        try
        {
            object = Class.forName(classPackagePath).newInstance();
        }
        catch (Exception e)
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++exception occurs for newInstance");
            return;
        }

        // 这里要用getDeclaredFields，它能获取所有属性，而getFields只能获取public属性
        Field[] fields = object.getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        for (Field field : fields)
        {
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
            String setMethodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);

            try {
                Method methodGet = object.getClass().getMethod(getMethodName);
                Method methodSet = object.getClass().getMethod(setMethodName, field.getType());
                // 调用get方法
                methodGet.setAccessible(true);
                methodGet.invoke(object);

                // 调用set方法
                methodSet.setAccessible(true);
                methodSet.invoke(object, field.getType().newInstance());

            } catch (NoSuchMethodException | IllegalAccessException e) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++exception occurs for invoke method");
            }

        }

    }

    /**
     * 匹配get方法
     * @param functionName 方法名
     * @return 是否是get方法
     */
    private static boolean isFunctionStartWithGet(String functionName)
    {
        String rule = "^get";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(functionName);

        return m.find();
    }
}
