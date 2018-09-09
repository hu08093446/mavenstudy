package idgenerator.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.Map;

@Configuration
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Inject applicationContext for SpringUtils.");
        context = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        isInjected();
        return (T) context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        isInjected();
        return context.getBean(requiredType);
    }

    public static <T> T getOneBeanOfType(Class<T> type) {
        Map<String, T> beanMap = context.getBeansOfType(type);
        Iterator<T> iterator = beanMap.values().iterator();
        if(iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    private static void isInjected() {
        if(null == context) {
            throw new RuntimeException("springUtils applicationContext is not injected.");
        }
    }
}
