package deepcopy;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        testImplementsSerializable();
        testApacheCommon();
    }

    /**
     * 使用apache提供的深度克隆工具
     * 查看源码可以发现，这样只是便捷而已，其实速度很慢，不推荐
     */
    private static void testApacheCommon() {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person("1", 1));
            add(new Person("2", 2));
            add(new Person("3", 3));
        }};

        Shoping shoping = new Shoping(personList.size(), personList);
        Shoping shopA = SerializationUtils.clone(shoping);

        for (Person person : shoping.getPersonList()) {
            person.setAge(100);
        }
        System.out.println(JSON.toJSONString(shoping));
        System.out.println(JSON.toJSONString(shopA));
    }

    /**
     * 测试Serializable接口的继承性质
     * Student继承了Person，Person实现了Serializable接口，那就意味着Student也实现了改接口
     * 继而就可以进行序列化和反序列化了
     */
    private static void testImplementsSerializable() throws IOException, ClassNotFoundException {
        Person personA = new Person("huke", 27);
        Person personB = DeepCopyUtil.deepCopy(personA);
        personA.setAge(28);
        System.out.println(JSON.toJSONString(personA));
        System.out.println(JSON.toJSONString(personB));

        Student stuA = new Student("middle");
        Student stuB = DeepCopyUtil.deepCopy(stuA);
        stuA.setGrade("advanced");
        stuA.setAge(22);

        System.out.println(JSON.toJSONString(stuA));
        System.out.println(JSON.toJSONString(stuB));
    }

    /**
     * 比较fastJson和java序列化的速度
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void compareFastJsonAndserialization() throws IOException, ClassNotFoundException {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person("1", 1));
            add(new Person("2", 2));
            add(new Person("3", 3));
        }};

        Shoping shoping = new Shoping(personList.size(), personList);

        // java 函数内部不允许使用static，static只能修饰类的属性或者成员函数
//        final static int cnt = 10000;
        final int cnt = 10000;

        Shoping shopA = new Shoping();
        Shoping shopB = new Shoping();

        long timeA = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            shopA = DeepCopyUtil.deepCopy(shoping);
        }
        long timeB = System.currentTimeMillis();
        System.out.println(timeB - timeA);

        // 使用fastjson来完成深度拷贝，性能更好
        long timeC = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            shopB = JSON.parseObject(JSON.toJSONString(shoping), Shoping.class);
        }
        System.out.println(System.currentTimeMillis() - timeC);

        for (Person person : shoping.getPersonList()) {
            person.setAge(100);
        }

        System.out.println(JSON.toJSONString(shoping));
        System.out.println(JSON.toJSONString(shopA));
        System.out.println(JSON.toJSONString(shopB));
    }

}
