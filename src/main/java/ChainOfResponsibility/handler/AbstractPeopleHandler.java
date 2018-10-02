package ChainOfResponsibility.handler;

import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPeopleHandler implements PeopleHandlerInterface {

    /** 记录日志 */
    public static Logger LOG = LoggerFactory.getLogger(AbstractPeopleHandler.class);

    @Override
    public void handle(ParamObject paramObject, OriginParam originParam) {
        System.out.println("--------------------------华丽丽的分割线---------------------------");
    }
}
