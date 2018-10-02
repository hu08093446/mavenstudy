package ChainOfResponsibility.PeopleProcess;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

public interface ProcessInterface {
    void process(OriginParam originParam, ParamObject paramObject);
}
