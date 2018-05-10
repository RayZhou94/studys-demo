package ioc.context;

import annotations.Component;
import ioc.beans.DefaultBeanFactory;

/**
 * @author zhiqiu
 */
public class DefaultContext extends AbstractContext {

    private DefaultBeanFactory defaultBeanFactory;

    public DefaultContext(String packageName){
        defaultBeanFactory = new DefaultBeanFactory();
        defaultBeanFactory.loadResource(packageName, Component.class);
        defaultBeanFactory.createAllBeans();
        loadBeanFactory(defaultBeanFactory);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }
}
