package learnjava8.parallerstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class StreamMain {
    private static Logger LOG = LoggerFactory.getLogger(StreamMain.class);

    public static void main(String[] args) throws InterruptedException
    {
//        testParallelStreamA(genIntegerList());
        testParallelStreamB(genIntegerList());
//        testStreamA(genIntegerList());
    }

    private static List<Integer> genIntegerList() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));

        return listOfIntegers;
    }

    private static void testParallelStreamA(List<Integer> listOfIntegers) {

//        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());;
        // 并发操作需要使用线程安全的List
        List<Integer> parallelStorage = new CopyOnWriteArrayList<>();
        listOfIntegers
                .parallelStream()
                // Don't do this! It uses a stateful lambda expression.
                // 由于是多线程操作，所以这里是无法保证加入的顺序的
                .map(e -> {
                    parallelStorage.add(e);
                    return e;
                })
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
        parallelStorage
                .forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

    /**
     * 并行流
     * @param listOfInteger 整数列表
     */
    private static void testParallelStreamB(List<Integer> listOfInteger) {
        AtomicBoolean failed = new AtomicBoolean(false);
        listOfInteger
                .parallelStream()
                .map(e -> {
                    try {
                        doSomething(e);
                    } catch (Exception e1) {
                        LOG.error("exception occurs, e = [{}]", e);
                        failed.set(true);
                    }
                    return e;
                }).forEachOrdered(System.out::println); // 如果结果没啥用，也可用count()触发前面的流操作
        // 整个并行操作执行完成之后，流程才会向下走
        // 这样当出现异常时，根据failed的值，可以从主线程向外抛出
        if (Objects.equals(true, failed.get())) {
            throw new RuntimeException("exception occurs..................");
        }
    }

    /**
     * 串行流，目前看是即使出现了异常，整个流也会跑完
     */
    private static void testStreamA(List<Integer> listOfInteger) {
        AtomicBoolean failed = new AtomicBoolean(false);

        listOfInteger.stream().peek(
                e -> {
                    try {
                        doSomething(e);
                    } catch (Exception e1) {
                        LOG.error("exception occurs, e = [{}]", e);
                        failed.set(true);
                    }
                }
        ).count(); //此处的count或者collect等操作必须加一个，不然前面的操作根本就不执行，此处含有触发功能的意思
        if (Objects.equals(true, failed.get())) {
            throw new RuntimeException("exception occurs..................");
        }
    }

    private static void doSomething(Integer value) throws InterruptedException {
        LOG.info("------------value = [{}]", value);
        TimeUnit.SECONDS.sleep(3);
        if (Objects.equals(value, 4)) {
            throw new RuntimeException("exception.");
        }

    }

}
