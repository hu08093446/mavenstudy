package deepcopy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shoping implements Serializable{
    private static final long serialVersionUID = -8583227534494445447L;

    private Integer cnt;

    private List<Person> personList = new ArrayList<>();

    public Shoping() {}

    public Shoping(Integer cnt, List<Person> personList) {
        this.cnt = cnt;
        this.personList = personList;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Shoping{" +
                "cnt=" + cnt +
                ", personList=" + personList +
                '}';
    }
}
