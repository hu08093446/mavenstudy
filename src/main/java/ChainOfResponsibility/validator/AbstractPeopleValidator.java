package ChainOfResponsibility.validator;

import ChainOfResponsibility.model.OriginParam;

public abstract class AbstractPeopleValidator implements PeopleValidatorInterface{

    @Override
    public void validate(OriginParam originParam) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
