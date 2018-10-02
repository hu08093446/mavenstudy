package ChainOfResponsibility.handler;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

public interface PeopleHandlerInterface {
    void handle(ParamObject paramObject, OriginParam originParam);
}
