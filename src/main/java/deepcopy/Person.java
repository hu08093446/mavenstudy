package deepcopy;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 8190598584720141277L;

    private String name;

    private Integer age;

    public Person() {}

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

        if (!name.equals(person.name)) return false;
        return age.equals(person.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

    @Override
    public int compareTo(Person o) {
        int result = o.age < this.age ? 1 : (Objects.equals(o.age, this.age) ? 0 : -1);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
