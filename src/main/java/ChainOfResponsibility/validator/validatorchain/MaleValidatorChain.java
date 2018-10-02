package ChainOfResponsibility.validator.validatorchain;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.validator.PeopleValidatorInterface;
import ChainOfResponsibility.validator.concretevalidator.PeopleAgeValidator;
import ChainOfResponsibility.validator.concretevalidator.PeopleGenderValidator;
import ChainOfResponsibility.validator.concretevalidator.PeopleNameValidator;

import java.util.ArrayList;
import java.util.List;

public class MaleValidatorChain implements PeopleValidatorInterface {

    private static PeopleValidatorInterface validatorA = new PeopleAgeValidator();

    private static PeopleValidatorInterface validatorB = new PeopleGenderValidator();

    private static PeopleValidatorInterface validatorC = new PeopleNameValidator();

    private static List<PeopleValidatorInterface> validatorList = new ArrayList<>(5);

    static {
        validatorList.add(validatorA);
        validatorList.add(validatorB);
        validatorList.add(validatorC);
    }

    @Override
    public void validate(OriginParam originParam) {
        for (PeopleValidatorInterface validator : validatorList) {
            validator.validate(originParam);
        }
    }
}
