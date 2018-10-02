package ChainOfResponsibility.PeopleProcess;

import ChainOfResponsibility.handler.handlerchain.FemaleHandlerChain;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import ChainOfResponsibility.validator.validatorchain.FemaleValidatorChain;

public class FemaleProcess extends  AbstractProcess{

    static {
        validatorChain = new FemaleValidatorChain();
        handlerChain = new FemaleHandlerChain();
    }

}
