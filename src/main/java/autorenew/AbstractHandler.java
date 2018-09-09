package autorenew;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractHandler implements Handler {
    private static final AtomicInteger CNT= new AtomicInteger(0);

    // 每一个Handler都要赋一个不同的值给它，必须的
    private Integer sequence;

    public AbstractHandler() {

    }

    public AbstractHandler(Integer temp) {
        if (null != temp) {
            sequence = temp;
        } else {
            sequence = CNT.getAndIncrement();
        }
        System.out.println("------------------------------------------abstractHandler constructor");
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 下面的equals函数满足： 自反性、对称性、传递性、一致性，所以是OK的
     * 在在继承体系的父类中使用equals函数是容易出问题的，请参考effective java 第32页左右的内容
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AbstractHandler)) return false;
        AbstractHandler that = (AbstractHandler) o;
        return Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sequence);
    }
}
