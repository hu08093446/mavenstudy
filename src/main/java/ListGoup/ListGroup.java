package ListGoup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将一个list按照指定的步长（step）进行分组，可以用于一批一批完成某件事情
 * 譬如一批一批的写入数据库以防止锁表时间太长，一批一批的发送事件以防止一次发送的事件体过大等等
 */
public class ListGroup {

    public static <T> List<List<T>> groupList(List<T> list, final int step) {
        List<List<T>> listGroup = new ArrayList<>(10);
        if (null == list || list.isEmpty()) {
            return listGroup;
        }

        int index = 0;
        while(index < list.size() ) {
            int toIndex = (index + step) > list.size() ? list.size() : (index + step);
            // sublist是一个截取list的方法，左闭右开，它包括左边索引指定的元素，但是不包括右边
            listGroup.add(list.subList(index, toIndex));
            index = index + step;
        }

        return listGroup;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>(10);
        strList.addAll(Arrays.asList("1", "2", "3", "4", "5"));

        System.out.println(groupList(strList, 1));
        System.out.println(groupList(strList, 2));
        System.out.println(groupList(strList, 3));
        System.out.println(groupList(strList, 4));
        System.out.println(groupList(strList, 5));
        System.out.println(groupList(strList, 6));
        System.out.println(groupList(new ArrayList<String>(), 1));
        System.out.println(groupList(null, 1));
    }
}
