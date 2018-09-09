package learnannotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huke
 */
public class AnnoteTest {
    public static void main(String[] args) {

    }

    @Override
    @MethodAnnote(date = "2018/3/7", revision = 2, comments = "girl")
    public String toString() {
        return "I want to learn more things.";
    }

    @Deprecated
    @MethodAnnote(date = "2018/3/7", revision = 3, comments = "fighting")
    public static void methodA() {
        System.out.println("I need more money");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MethodAnnote(author = "cshuangshuang", date = "2018/3/7", comments = "girl, beautiful")
    public static void methodB() {
        List<String> nameList = new ArrayList<>(5);
        nameList.add("huke");
        nameList.add("cshuangshuang");

        methodA();
    }
}
