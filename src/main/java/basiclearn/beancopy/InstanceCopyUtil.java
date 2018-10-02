package basiclearn.beancopy;


import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InstanceCopyUtil {

    @SuppressWarnings("unchecked")
    public static void copyInstance(Object src, Object dest) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class destClass = dest.getClass();
        Class srcClass = src.getClass();

        Field[] allDestFields = destClass.getDeclaredFields();
        Set<String> srcMethodNameSet = Arrays.stream(srcClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toSet());
        for (Field field : allDestFields) {
            String fieldName = field.getName();
            String methodSuffix = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String destSetMethodName = "set" + methodSuffix;
            String destGetMethodName = "get" + methodSuffix;
            String srcGetMethodName = "get" + methodSuffix;

            // src中没有对应的属性，无法赋值
            if (!srcMethodNameSet.contains(srcGetMethodName)) {
                continue;
            }

            Class destReturnType = destClass.getDeclaredMethod(destGetMethodName,  null).getReturnType();
            Class srcReturnType = srcClass.getDeclaredMethod(srcGetMethodName,  null).getReturnType();

            if (!destReturnType.isAssignableFrom(srcReturnType)) {
                continue;
            }

            Object value = srcClass.getDeclaredMethod(srcGetMethodName, null).invoke(src, null);

            destClass.getDeclaredMethod(destSetMethodName, destReturnType).invoke(dest, value);
        }
    }


    public static void main(String[] args) {
        Person person = new Person("hu", 27, true, "140@", new Student(1000, "student", "junor"));
        People people = new People();

        try {
            copyInstance(person, people);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(people);

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Person {
        private String name;

        private int age;

        private Boolean flag;

        private String email;

        private Student student;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class People {
        private String name;

        private int age;

        private Boolean flag;

        private String address;

        private Job student;
    }

    @Getter
    @Setter
    private abstract static class Job {
        private Integer salary;

        private String name;

        protected Job() {}

        protected Job(Integer salary, String name) {
            this.salary = salary;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "salary=" + salary +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Getter
    @Setter
    private static class Student extends Job {
        private String grade;

        public Student() {}

        public Student(String grade) {
            this.grade = grade;
        }

        public Student(Integer salary, String name, String grade) {
            super(salary, name);
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "grade='" + grade + '\'' +
                    "} " + super.toString();
        }
    }

}
