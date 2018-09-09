package basiclearn.nopublicclass;

public class ClassA {
    public static void main(String[] args) {
        PublicClass sideClass = new PublicClass();
        Person person = new Person();
        person.setName("huke");
        sideClass.setPerson(person);

    }
}
