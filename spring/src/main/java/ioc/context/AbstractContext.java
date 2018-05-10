package ioc.context;

import ioc.beans.BeanFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiqiu
 */
public abstract class AbstractContext implements Context {

    private BeanFactory beanFactory;

    public void loadBeanFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public <T> T getBean(String name) {
        return beanFactory.getBean(name);
    }
}
