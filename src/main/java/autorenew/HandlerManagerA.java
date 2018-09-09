package autorenew;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个manager实现的是Handler，所以是根据对象的地址来区分对象是否相同的
 * 很显然，不同对象的内存地址肯定不同，所以registerHandler函数中的contains判断并没有用
 */
public class HandlerManagerA implements Handler {

    private List<Handler> handlerList = new ArrayList<>(3);

    public HandlerManagerA registerHandler(Handler handler) {
        if (!handlerList.contains(handler)) {
            handlerList.add(handler);
        }
        return this;
    }

    @Override
    public void handle(ComplexParam param) {
        for (Handler handler : handlerList) {
            // getClass()可以获得对象运行时的实际类型
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++" + handler.getClass());
            handler.handle(param);
        }
    }
}
