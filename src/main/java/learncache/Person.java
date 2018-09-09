package learncache;

import com.google.common.base.Objects;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;

    private Integer age;

    public Person() {}

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println("------------------------------------invoke get method " + getClass().getName());
        return name;
    }

    public void setName(String name) {
        System.out.println("------------------------------------invoke set method " + getClass().getName());
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(name, person.name) &&
                Objects.equal(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
