package regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnRegex {
    private static final Logger LOG = LoggerFactory.getLogger(LearnRegex.class);

    public static void main(String[] args) {
//        regexMethodA();

//        regexMethodB();

//        learnAppendMethod();

        regexMethodC();
    }

    private static void regexMethodC() {
        String str = "xxxxxxxxxxxxxxxxxssdddddxss";

        String pattern = "[abc]|xss|ddq";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        while (m.find()) {
            System.out.println("oh, no...");
            System.out.println(m.group(0));
            System.out.println(m.start());
            System.out.println(m.end());
        }
    }

    private static void regexMethodA() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        // java需要对‘\’进行转义，其实真正的正则表达式是这样:(\D*)(\d+)(.*)
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        int count = 0;

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find( )) {
            LOG.info("--------cnt = [{}]", ++count);
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        }
    }

    private static void regexMethodB() {
        String regex = "\\bcat\\b";
        String input = "cat cat cat cattie cat";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input); // 获取 matcher 对象
        int count = 0;

        while(m.find()) {
            count++;
            // 只有一个分组，0
            LOG.info("group 0 = [{}]", m.group(0));
            System.out.println("Match number "+count);
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
        }
    }

    private static void learnAppendMethod() {

        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoob";
        String REPLACE = "-";
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
        }
        // 下面这句要不要感觉问题都不大呀？？？
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
