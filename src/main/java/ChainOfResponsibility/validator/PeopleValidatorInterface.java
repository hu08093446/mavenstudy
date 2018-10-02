package ChainOfResponsibility.validator;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

public interface PeopleValidatorInterface {
    void validate(OriginParam originParam);
}
