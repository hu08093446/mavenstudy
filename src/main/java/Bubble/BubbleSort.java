package Bubble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Administrator
 */
public class BubbleSort {

    private static List<Person> personList = initPersonList();

    public static void main(String[] args) {
//        generalSort(personList);

        collectorSort(personList);
    }

    private static void printList(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person.getName() + " " + person.getAge());
        }
    }

    private static List<Person> initPersonList() {
        List<Person> resultList =
                new ArrayList<Person>() {
                    {
                        add(new Person("huke", 27));
                        add(new Person("shuangshuang", 25));
                        add(new Person("lishuang", 12));
                        add(new Person("yudazhi", 35));
                    }
                };

        return resultList;
    }

    /** @param objList */
    private static void sort(List<Person> objList) {
        if (objList.isEmpty()) {
            return;
        }

        int len = objList.size();
        Person person = null;
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if (objList.get(j).getAge() < objList.get(j - 1).getAge()) {
                    // 交换
                    person = objList.get(j);
                    objList.set(j, objList.get(j - 1));
                    objList.set(j - 1, person);
                }
            }
        }
    }

    private static void generalSort(List<Person> objList) {
        printList(personList);
        sort(personList);
        System.out.println("==============================================");
        printList(personList);
    }

    private static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int seq1 = Integer.valueOf(o1.getAge());
            int seq2 = Integer.valueOf(o2.getAge());

            return seq1 - seq2;
        }
    }

    // 这种排序的方式也很不错
    private static void collectorSort(List<Person> objList) {
        printList(objList);
        System.out.println("------------------------------");
        Collections.sort(objList, new PersonComparator());
        printList(objList);
    }

    /** 这种找到最小的然后放入另一个list中的方法，有问题，感觉并不好 */
    //    private static void sortOrder(List<Person> objList, List<Person> resultList) {
    //        if(null == objList || objList.isEmpty()) {
    //            resultList = objList;
    //            return;
    //        }
    //
    //        Person personMin = null;
    //        while(!objList.isEmpty()) {
    //            personTemp = findMinAgePerson(objList, personMin);
    //            // 将当前最小的从原list里面去掉
    //
    //            resultList.add(personTemp);
    //        }
    //    }
    //
    //    /**
    //     * 当入参为Null或空时，返回结果为-1
    //     * @param objList
    //     * @return
    //     */
    //    private static int findMinAgePerson(List<Person> objList, Person personMin) {
    //        if(null == objList || objList.isEmpty()) {
    //            personMin = null;
    //            return -1;
    //        }
    //        personMin = objList.get(0);
    //        Integer ageMin = personMin.getAge();
    //        int indexMin = 0;
    //
    //        for(Person person : objList) {
    //            if(person.getAge() < ageMin) {
    //                personMin = person;
    //                ageMin = person.getAge();
    //            }
    //        }
    //
    //        return indexMin;
    //    }
}
