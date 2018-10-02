package ChainOfResponsibility.PeopleProcess;

import ChainOfResponsibility.handler.PeopleHandlerInterface;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import ChainOfResponsibility.validator.PeopleValidatorInterface;

public class AbstractProcess implements ProcessInterface {

    protected static PeopleValidatorInterface validatorChain;

    protected static PeopleHandlerInterface handlerChain;

    @Override
    public void process(OriginParam originParam, ParamObject paramObject) {
        if (null == validatorChain || null == handlerChain) {
            throw new UnsupportedOperationException("process is not supported");
        }

        validatorChain.validate(originParam);

        handlerChain.handle(paramObject, originParam);
    }

}
