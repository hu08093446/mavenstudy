package learnproxy;

/**
 * @author huke
 * 如果想要使用动态代理，那么方法的返回类型最好别是基本类型
 * 因为在异步执行的情况下，无法立即拿到返回结果，如果方法返回的是类类型，那么此时invoke方法可以返回null，因为类型为null是可以的
 * 但是如果为基本类型，譬如int类型，那么null在转换为int时会抛出NPE
 */
public interface Person {

    String sing(String name);

    String dance(String name);

    Integer shoot(int num);

    String fly(String name, int num);
}
