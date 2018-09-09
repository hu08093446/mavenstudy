package basiclearn.interfaceandabstract;

public interface WallFacer {
    /**
     * 接口中的方法默认是 public abstract
     * 面壁人的思考都是一样的
     */
    void thinking();

    /**
     * 面壁人的行动大致一样，略有不同
     */
    void actioning();

    /**
     * 面壁人最终造成的结果各不相同
     */
    void resulting();

    /**
     * 评价
     */
    void evaluationing();

    static void doSomething() {
        System.out.println("fuck, interface can has static method");
    }
}
