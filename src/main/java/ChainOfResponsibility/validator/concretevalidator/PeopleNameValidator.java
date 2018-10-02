package ChainOfResponsibility.validator.concretevalidator;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.validator.AbstractPeopleValidator;
import org.apache.commons.lang3.StringUtils;

public class PeopleNameValidator extends AbstractPeopleValidator {

    @Override
    public void validate(OriginParam originParam) {
        super.validate(originParam);
        validateName(originParam);
    }

    private void validateName(OriginParam originParam) {
        String name = originParam.getName();
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("people name should not be empty");
        }

        if (!StringUtils.startsWithIgnoreCase(name, "h")) {
            throw new RuntimeException("people name should starts with h or H");
        }
    }
}
