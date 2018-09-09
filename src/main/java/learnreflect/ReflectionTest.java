package learnreflect;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectionTest {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectionTest.class);

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        methodA();
        methodB();
    }

    private static void methodB() throws IllegalAccessException {
        CommonRsp rsp = new CommonRsp("1111", "failed");
        LOG.info("origin, CommonRsp : [{}]", JSON.toJSONString(rsp));

        changeAttributes(rsp);
        LOG.info("after change, CommonRsp : [{}]", JSON.toJSONString(rsp));
    }

    private static <T> void changeAttributes(T t) throws IllegalAccessException {
        // attention getFields 只能拿到public权限的属性
        Field[] fields = t.getClass().getDeclaredFields();
//        Field.setAccessible(fields, true);

        for (Field field : fields) {
            boolean flag = false;
            if (!field.isAccessible()) {
                field.setAccessible(true);
                flag = true;
            }

            if (Objects.equals(field.getName(), "retCode")) {
                field.set(t, "0000");
            }
            else if (Objects.equals(field.getName(), "retMsg")) {
                field.set(t, "success");
            }

            if (flag) {
                field.setAccessible(false);
            }
        }

//        Field.setAccessible(fields, false);
    }

    private static void methodA() throws NoSuchFieldException, IllegalAccessException{
        Employee harry = new Employee("Harry Hacker", 35000, 10, 1, 1989);

        Class cl = harry.getClass();
        System.out.println(cl.isPrimitive());
        Field f = cl.getDeclaredField("name");
        f.setAccessible(true);
        Object v = f.get(harry);
        System.out.println(v);

        printFields(harry);
        System.out.println("\n\n");

        f.set(harry, "shuangshuang");
        v = f.get(harry);

        System.out.println(v);
        printFields(harry);
    }

    public static <T> void printFields(T obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        StringBuilder builder = new StringBuilder();

        for (Field f : fields) {
            builder.setLength(0);
            builder.append(f.getName()).append(" -> ").append(f.get(obj));
            System.out.println(builder.toString());
        }

    }
}
