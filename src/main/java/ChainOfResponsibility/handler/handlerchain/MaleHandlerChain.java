package ChainOfResponsibility.handler.handlerchain;

import ChainOfResponsibility.handler.AbstractPeopleHandler;
import ChainOfResponsibility.handler.PeopleHandlerInterface;
import ChainOfResponsibility.handler.concretehandler.PeopleAgeHandler;
import ChainOfResponsibility.handler.concretehandler.PeopleGenderHandler;
import ChainOfResponsibility.handler.concretehandler.PeopleNameHandler;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

import java.util.ArrayList;
import java.util.List;

public class MaleHandlerChain implements PeopleHandlerInterface {

    private static PeopleHandlerInterface handlerA = new PeopleAgeHandler();

    private static PeopleHandlerInterface handlerB = new PeopleGenderHandler();

    private static PeopleHandlerInterface handlerC = new PeopleNameHandler();

    private static List<PeopleHandlerInterface> handlerList = new ArrayList<>(5);

    static {
        handlerList.add(handlerA);
        handlerList.add(handlerB);
        handlerList.add(handlerC);
    }

    @Override
    public void handle(ParamObject paramObject, OriginParam originParam) {
        for (PeopleHandlerInterface handler : handlerList) {
            handler.handle(paramObject, originParam);
        }
    }
}
