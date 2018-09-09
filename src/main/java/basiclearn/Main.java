package basiclearn;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        learnMap();
        watchString();
    }

    private static void watchString() {
        String str = "youyouyou";
        System.out.println(str.replace("y", "w"));
        System.out.println(str);
        System.out.println(str.substring(0, 2));
        System.out.println(str);
    }

    private static void learnMap() {
        Map<String, Integer> mapA = new HashMap<>();
        mapA.put("1", 1);
        mapA.put("2", 2);
        mapA.putIfAbsent("2", 3);
        mapA.putIfAbsent("3", 3);
        System.out.println(mapA);

        Map<String, Integer> mapB = new HashMap<>();
        mapB.putAll(mapA);
        mapB.replace("2", 3);
        mapB.remove("2", 1);
        System.out.println(mapB.getOrDefault("3", 4));
        System.out.println(mapB);
        mapB.keySet().remove("1");
        System.out.println(mapB);
        System.out.println(mapB.size());
        mapB.values().remove(3);
        System.out.println(mapB);
    }

    @Test
    public void testArrayList() {
        List<Order> orders = new ArrayList<>();

        List<OrderLine> orderLinesA = new ArrayList<OrderLine>() {{
           add(new OrderLine("line-01-001", 1));
           add(new OrderLine("line-01-002", 2));
        }};

        Order order = new Order();
        order.setCustomerId("huke");
        order.setOrderLines(orderLinesA);
        orders.add(order);

        List<OrderLine> orderLinesB = new ArrayList<OrderLine>() {{
            add(new OrderLine("line-02-001", 3));
            add(new OrderLine("line-02-002", 5));
        }};

        order = new Order();
        order.setCustomerId("chengss");
        order.setOrderLines(orderLinesB);
        orders.add(order);

        System.out.println(JSON.toJSONString(orders));

        List<String> lineIdList = new ArrayList<>();
        List<OrderLine> orderLineList = new ArrayList<>();
        for(Order orderEntity : orders) {
            List<OrderLine> orderLines = orderEntity.getOrderLines();
            if(null == orderLines || orderLines.isEmpty()) {
                continue;
            }
            for(OrderLine orderLine : orderEntity.getOrderLines()) {
                orderLineList.add(orderLine);
            }
        }

        // 处理orderLineList中的orderLine就相当于处理原始orders列表中的OrderLine，因为Java都是引用
        orderLineList.forEach(o -> {
            Integer num = o.getNum();
            switch (num) {
                case 1:
                    o.setPrice(1.2);
                    break;
                case 2:
                    o.setPrice(2.4);
                    break;
                case 3:
                    o.setPrice(3.6);
                    break;
                default:
                    o.setPrice(100.0);
                    break;
            }
        });

        System.out.println(JSON.toJSONString(orders));
    }

    @Test
    public void testB() {
        List<String> strList = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        for(int i = 0; i < strList.size(); i++) {
            System.out.println(strList.get(i));
        }


        for(Iterator<String> iterator = strList.iterator(); iterator.hasNext(); ){
            String str = iterator.next();
            System.out.println(str);
            if( Objects.equals(str, "a")) {
                iterator.remove();
            }
        }

        System.out.println(JSON.toJSONString(strList));

        // 线程安全的
        Vector<String> strVec = new Vector<>();
        strVec.add("c");
        strVec.add("d");
        // 取交集
        strVec.retainAll(strList);
        System.out.println(strVec);
//        strList.retainAll(strVec);

    }

    @Test
    public void testC() {
        List<Integer> aList = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};

//        List<Integer> bList = new LinkedList<>(aList);
        // 相比与上面这种方式，addall的速度更快
        List<Integer> bList = new LinkedList<>();
        bList.addAll(aList);
        System.out.println(bList);
    }

    @Test
    public void testD() {
        List<String> c = new CopyOnWriteArrayList<>();
        c.add("something");
        ListIterator<String> it = c.listIterator();
        c.add("one str");

        try {
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }

        System.out.println(c);
    }

    @Test
    public void testSwitchCase() {
        int value = 2;
        switch (value) {
            case 1: {
                System.out.println("case 1");
                break;
            }
            case 2: {
                System.out.println("case 2");
                break;
            }
            default: {
                System.out.println("another case");
            }
        }
    }

    @Test
    public void testFormParam() {
        int a = 2;
        System.out.println(useFormalParam(a));
    }

    /**
     * 此处代码对形参赋值，可以，但是垃圾，不要这么搞
     * @param initValue 初值
     * @return 处理的结果
     */
    private int useFormalParam(int initValue) {
        initValue = initValue * 2;
        return initValue;
    }

}


