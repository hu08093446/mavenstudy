package basiclearn.beancopy;

import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InstanceCopyUtil {

    public static void copyInstance(Object src, Object dest) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class destClass = dest.getClass();
        Class srcClass = src.getClass();

        Field[] allDestFields = destClass.getDeclaredFields();
        Set<String> srcMethodNameSet = Arrays.stream(srcClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toSet());
        for (Field field : allDestFields) {
            String fieldName = field.getName();
            String methodSuffix = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
            String destSetMethodName = "set" + methodSuffix;
            String destGetMethodName = "get" + methodSuffix;
            String srcGetMethodName = "get" + methodSuffix;
            Class returnType = destClass.getDeclaredMethod(destGetMethodName, null).getReturnType();

            // src中没有对应的属性，无法赋值
            if (!srcMethodNameSet.contains(srcGetMethodName)) {
                continue;
            }

            Object value = srcClass.getDeclaredMethod(srcGetMethodName, null).invoke(src, null);
            destClass.getDeclaredMethod(destSetMethodName, returnType).invoke(dest, value);
        }
    }

    public static void main(String[] args) {
//        Person person = new Person("hu", 27, true);

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class Person {
        private String name;

        private Integer age;

        private Boolean flag;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class People {
        private String name;

        private Integer age;

        private Boolean flag;
    }

}
