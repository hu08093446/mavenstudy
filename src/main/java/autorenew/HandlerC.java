package autorenew;

import org.springframework.stereotype.Component;

@Component("handlerC")
public class HandlerC extends AbstractHandler{

    /**
     * 下面的两个构造函数用于测试自己编写的equals函数是否起了作用
     */
//    public HandlerC() {
//        super(1);
//    }

    public HandlerC() {
        super(null);
    }

    @Override
    public void handle(ComplexParam param) {
        param.getStrList().add("handlderC");
    }
}
