package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFindInvalidCharacter {

    public static void main(String[] args) {
        String str = "[]";
        String regex = "[/{}|'\"\\\\*\\[\\]]";

        System.out.println(check(str, regex));

//        String[] array = new String[]{"123", "abc"};
//        array = Arrays.copyOf(array, 2*array.length + 1);
//
//        System.out.println(JSON.toJSONString(array));
    }

    private static boolean check(String str, String regex) {
        boolean pass = true;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            pass = false;
        }

        return pass;
    }
}
