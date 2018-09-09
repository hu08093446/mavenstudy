package localcache;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class LocalCacheTest {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>(5);
        strList.add("a");
        strList.add("b");

        LocalCache.set("str", strList);

        User user = new User("huke", 27, strList);
        LocalCache.set("info", user);

        List<String> result = LocalCache.get("str");
        System.out.println(result);
        User userA = LocalCache.get("info");
        System.out.println(JSON.toJSONString(userA));
    }
}

class User {
    private String name;
    private Integer age;
    private List<String> school = new ArrayList<>();

    public User(String name, Integer age, List<String> school) {
        this.name = name;
        this.age = age;
        this.school = school;
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

    public List<String> getSchool() {
        return school;
    }

    public void setSchool(List<String> school) {
        this.school = school;
    }
}
