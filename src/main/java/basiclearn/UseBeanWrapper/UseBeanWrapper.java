package basiclearn.UseBeanWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

public class UseBeanWrapper {

    private static Logger LOG = LoggerFactory.getLogger(UseBeanWrapper.class);

    public static void main(String[] args) {
        beanWarpperUsing();
    }

    private static void beanWarpperUsing() {
        Person person = new Person();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(person);

        beanWrapper.setPropertyValue("age", 12);
        LOG.info("person is [{}]", person);

        PropertyValue value = new PropertyValue("name", "huke");
        beanWrapper.setPropertyValue(value);
        LOG.info("person is [{}]", person);
    }
}
