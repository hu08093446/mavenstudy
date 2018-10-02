package ChainOfResponsibility.validator.concretevalidator;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.validator.AbstractPeopleValidator;

public class PeopleAgeValidator extends AbstractPeopleValidator {

    private static final Integer MAX_AGE = 35;

    @Override
    public void validate(OriginParam originParam) {
        super.validate(originParam);
        validateAge(originParam);
    }

    private void validateAge(OriginParam originParam) {
        if (null == originParam.getAge()) {
            throw new RuntimeException("people age should not be null");
        }
        if (originParam.getAge() > MAX_AGE) {
            throw new RuntimeException("people age should be smaller than or equal to " + MAX_AGE);
        }
    }
}
