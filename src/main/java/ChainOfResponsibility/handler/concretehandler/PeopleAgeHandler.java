package ChainOfResponsibility.handler.concretehandler;

import ChainOfResponsibility.handler.AbstractPeopleHandler;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

public class PeopleAgeHandler extends AbstractPeopleHandler {

    private static final Integer TEN_YEAR = 10;

    @Override
    public void handle(ParamObject paramObject, OriginParam originParam) {
        super.handle(paramObject, originParam);
        ageAddTenYears(originParam, paramObject);
    }

    private void ageAddTenYears(OriginParam originParam, ParamObject paramObject) {
        Integer age = originParam.getAge() + TEN_YEAR;
        paramObject.setAge(age);
    }
}
