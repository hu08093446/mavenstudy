package learnreflect;

public class Employee {

    private String name;

    private Integer salary;

    private Integer month;

    private Integer day;

    private int year;

    public Employee() {

    }

    public Employee(String name, Integer salary, Integer month, Integer day, int year) {
        this.name = name;
        this.salary = salary;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        System.out.println("------------------------------------invoke get method " + getClass().getName());

        return salary;
    }

    public void setSalary(Integer salary) {
        System.out.println("------------------------------------invoke set method " + getClass().getName());

        this.salary = salary;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }
}
