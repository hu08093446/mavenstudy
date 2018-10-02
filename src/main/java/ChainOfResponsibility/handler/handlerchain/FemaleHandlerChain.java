package ChainOfResponsibility.handler.handlerchain;

import ChainOfResponsibility.handler.PeopleHandlerInterface;
import ChainOfResponsibility.handler.concretehandler.PeopleAgeHandler;
import ChainOfResponsibility.handler.concretehandler.PeopleGenderHandler;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

import java.util.ArrayList;
import java.util.List;

public class FemaleHandlerChain implements PeopleHandlerInterface {

    private static PeopleHandlerInterface handlerA = new PeopleAgeHandler();

    private static PeopleHandlerInterface handlerB = new PeopleGenderHandler();

    private static List<PeopleHandlerInterface> handlerList = new ArrayList<>(5);

    static {
        handlerList.add(handlerA);
        handlerList.add(handlerB);
    }
    @Override
    public void handle(ParamObject paramObject, OriginParam originParam) {
        for (PeopleHandlerInterface handler : handlerList) {
            handler.handle(paramObject, originParam);
        }
    }
}
