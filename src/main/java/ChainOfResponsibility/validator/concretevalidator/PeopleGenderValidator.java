package ChainOfResponsibility.validator.concretevalidator;

import ChainOfResponsibility.model.Gender;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.validator.AbstractPeopleValidator;

public class PeopleGenderValidator extends AbstractPeopleValidator {

    @Override
    public void validate(OriginParam originParam) {
        super.validate(originParam);
        validateGender(originParam);
    }

    private void validateGender(OriginParam originParam) {
        if (Gender.isNotLegalGender(originParam.getGender())) {
            throw new RuntimeException("people gender is illegal");
        }
    }
}
