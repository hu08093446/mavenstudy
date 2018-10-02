package ChainOfResponsibility.handler.concretehandler;

import ChainOfResponsibility.handler.AbstractPeopleHandler;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import org.apache.commons.lang3.StringUtils;

public class PeopleGenderHandler extends AbstractPeopleHandler {

    private static final String DEFAULT_GENDER = "male";

    @Override
    public void handle(ParamObject paramObject, OriginParam originParam) {
        super.handle(paramObject, originParam);
        fillGenderIfNecessary(originParam, paramObject);
    }

    private void fillGenderIfNecessary(OriginParam originParam, ParamObject paramObject) {
        if (StringUtils.isEmpty(originParam.getGender())) {
            paramObject.setGender(DEFAULT_GENDER);
        } else {
            paramObject.setGender(originParam.getGender());
        }
    }
}
