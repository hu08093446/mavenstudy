package basiclearn.nopublicclass;

public class PublicClass {
    private String id;

    private Person person = new Person();

    public PublicClass() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

class Person extends Annother {
    public void printFunc() {
        System.out.println("------------------hello nonpublic class.");
    }

    private String name = "name";

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        printFunc();
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void really() {
        System.out.println("++++++++++yes,really");
    }
}
