package ChainOfResponsibility.PeopleProcess;

import ChainOfResponsibility.handler.handlerchain.MaleHandlerChain;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import ChainOfResponsibility.validator.validatorchain.MaleValidatorChain;

public class MaleProcess extends AbstractProcess {

    static {
        validatorChain = new MaleValidatorChain();
        handlerChain = new MaleHandlerChain();
    }

}
