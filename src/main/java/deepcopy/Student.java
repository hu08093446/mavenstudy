package deepcopy;

public class Student extends Person{
    private static final long serialVersionUID = 7905929779362441136L;

    private String grade;

    public Student() {

    }

    public Student(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
