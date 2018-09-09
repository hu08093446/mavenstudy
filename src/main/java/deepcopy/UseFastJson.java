package deepcopy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UseFastJson {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person("1", 1));
            add(new Person("2", 2));
            add(new Person("3", 3));
        }};

        String str = JSON.toJSONString(personList);
        System.out.println(str);

        List<Person> perListA = JSON.parseArray(str, Person.class);
        System.out.println(perListA);

        JSONArray jsonArray = JSON.parseArray(str);
        for (Object person : jsonArray) {
            System.out.println(((JSONObject)person).getInteger("age"));
        }

        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            System.out.println(((JSONObject)it.next()).getString("name"));
        }

        Person personA = new Person("4", 4);
        String strA = JSON.toJSONString(personA);

        Person personB = JSON.parseObject(strA, Person.class);
        System.out.println(personB);
        JSONObject jsonObject = JSON.parseObject(strA);
        System.out.println(jsonObject.getString("name"));
    }

}
