package objectlearn;


interface Ainmal {
    void roa();
}

class Cat implements Ainmal{
    private int age;

    private String name;

    @Override
    public void roa() {
        System.out.println("miao miao miao.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Dog implements Ainmal{
    private int age;

    private String name;

    @Override
    public void roa() {
        System.out.println("wang wang wang.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class LearnObject {

    public static void main(String[] args) {
        boolean flag = false;

        Ainmal animal = flag ? new Cat() : new Dog();
        //如果animal是Object类型，下面的方法就无法调用，因为Object没有roa方法
        animal.roa();

        Object ani = flag ? new Cat() : new Dog();
    }
}
