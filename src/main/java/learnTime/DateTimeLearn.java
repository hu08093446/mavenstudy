package learnTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeLearn {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Timestamp转化为String:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//定义格式，不显示毫秒
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
        now = new Timestamp(now.getTime() - 300L);
        String str = df.format(now);
        System.out.println(str);


///String转化为Timestamp:
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df1.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        Date date = new Date();
        System.out.println(date.getTime());
        String time = df1.format(date.getTime() - 100L);
        Timestamp ts = Timestamp.valueOf(time);
        System.out.println(ts);
    }

}
