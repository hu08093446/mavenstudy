package ChainOfResponsibility.handler.concretehandler;

import ChainOfResponsibility.handler.AbstractPeopleHandler;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import org.apache.commons.lang3.StringUtils;

public class PeopleNameHandler extends AbstractPeopleHandler {
    @Override
    public void handle(ParamObject param, OriginParam originParam) {
        super.handle(param, originParam);
        reverseName(originParam, param);
    }

    private void reverseName(OriginParam originParam, ParamObject param) {
        String name = originParam.getName();
        param.setName(StringUtils.reverse(name));
    }
}
