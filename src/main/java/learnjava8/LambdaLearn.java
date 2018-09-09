package learnjava8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LambdaLearn {
    public static void main(String[] args) {

//        testMethodA();
        testMethodC();
    }

    /**
     * map是从一个流转换为另外一个流，正常情况下，map内部的过程最终是要加return语句返回的，
     * 但如果只有一条语句且没加大括号，可以把return语句省略掉，但这只是特殊的情况，有return才是通用的做法
     */
    private static void testMethodA() {
        List<String> nameList = new ArrayList<>(Arrays.asList("ZhiFuBao", "WeiXin"));
        List<String> lowerCaseNameList = nameList.stream().map(n -> {
            n = n + "LeiLe";
            return n.toLowerCase();
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(lowerCaseNameList));
    }

    private static void testMethodB() {
        // 下面的循环无法通过编译
//        for (final int i = 0; i <  4; i++) {
//
//        }

        // 这里的循环就没问题，应该是因为i的指向并没有改变，只是内部的值在改变
        for (final Integer i : Lists.newArrayList(1, 2, 3)) {

        }
    }

    private static void testMethodC() {
        List<School> schoolListA = new ArrayList<School>() {{
           add(new School("xiaoxue", "chengjiaoxiang"));
           add(new School("chuzhong", "chengguanzhen"));
           add(new School("gaozhong", "xiangyangjie"));
           add(new School("daxue", "xuzhou"));
        }};

        List<School> schoolListB = new ArrayList<School>() {{
            add(new School("xiaoxue", "guangdong"));
            add(new School("chuzhong", "shenzhen"));
            add(new School("gaozhong", "dongguan"));
            add(new School("daxue", "huizhou"));
        }};

        List<Person> personList = new ArrayList<Person>() {{
            add(new Person("huke", schoolListA));
            add(new Person("someone", schoolListB));
        }};

//        List<School> allSchoolList = personList.stream()
//                .flatMap(person -> person.getSchoolList().stream())
//                .collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(allSchoolList));

        personList.stream().filter(person -> Objects.equals(person.getName(), "huke")).map(
                person -> person.getSchoolList().stream()
                        .filter(school -> !Objects.equals(school.getAddress(), "xuzhou"))
                        .peek(school -> doSomethingWithSchool(school, person)).count()
        ).count();
    }

    private static void doSomethingWithSchool(School school, Person person) {
        System.out.println(person.getName());
        System.out.println(JSON.toJSONString(school));
    }

    private static class Person {
        String name;

        List<School> schoolList = new ArrayList<>();

        public Person() {}

        public Person(String name, List<School> schoolList) {
            this.name = name;
            this.schoolList = schoolList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<School> getSchoolList() {
            return schoolList;
        }

        public void setSchoolList(List<School> schoolList) {
            this.schoolList = schoolList;
        }
    }

    private static class School {
        private String name;

        private String address;

        public School() {}

        public School(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
