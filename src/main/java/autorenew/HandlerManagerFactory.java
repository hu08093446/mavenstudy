package autorenew;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

@Component("handlerManagerFactory")
public class HandlerManagerFactory {

    private static HandlerManagerA handlerManagerAOne = new HandlerManagerA();

    private static HandlerManagerA handlerManagerATwo = new HandlerManagerA();

    private static HandlerManagerA handlerManagerAThree = new HandlerManagerA();

    @Resource
    private AbstractHandler handlerA;

    @Resource
    private AbstractHandler handlerB;

    @Resource
    private AbstractHandler handlerC;

    @PostConstruct
    public void initMethod() {
        handlerManagerAOne
                .registerHandler(handlerA)
                .registerHandler(handlerB)
                .registerHandler(handlerC);

        handlerManagerATwo
                .registerHandler(handlerB)
                .registerHandler(handlerA)
                .registerHandler(handlerC);

        handlerManagerAThree
                .registerHandler(handlerC)
                .registerHandler(handlerA)
                .registerHandler(handlerB);
    }

    public static HandlerManagerA getHandlerManager(String type) {
        HandlerManagerA result;
        String temp = StringUtils.lowerCase(type);
        // 当switch的内容为空时，会抛出NPE，鲁棒性差，不建议用
//        switch (temp) {
//            case "one": {
//                result = handlerManagerAOne;
//                break;
//            }
//            case "two": {
//                result = handlerManagerATwo;
//                break;
//            }
//            case "three": {
//                result = handlerManagerAThree;
//                break;
//            }
//            default: {
//                result = handlerManagerAOne;
//                break;
//            }
//        }
        if (Objects.equals(temp, "one")) {
            result = handlerManagerAOne;
        } else if (Objects.equals(temp, "two")) {
            result = handlerManagerATwo;
        } else if (Objects.equals(temp, "three")) {
            result = handlerManagerAThree;
        } else {
            result = handlerManagerAOne;
        }

        return result;
    }

}
