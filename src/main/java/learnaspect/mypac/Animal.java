package learnaspect.mypac;

public class Animal {

    private String name;

    private boolean isroa;

    public Animal() {}

    public Animal(String name, boolean isroa) {
        this.name = name;
        this.isroa = isroa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsroa() {
        return isroa;
    }

    public void setIsroa(boolean isroa) {
        this.isroa = isroa;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", isroa=" + isroa +
                '}';
    }
}
