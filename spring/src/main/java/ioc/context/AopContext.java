package ioc.context;

import annotations.Component;
import aop.ProxyBean;
import aop.advice.AdviceUtil;
import aop.advice.Advised;
import aop.aspect.AopSupport;
import ioc.beans.BeanDefinition;
import ioc.beans.BeanFactory;
import ioc.beans.DefaultBeanFactory;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class AopContext extends AbstractContext {

    private AopSupport aopSupport;

    private String packageName;

    private DefaultBeanFactory defaultBeanFactory;

    public AopContext(String packageName) {
        this.packageName = packageName;
        defaultBeanFactory = new DefaultBeanFactory();
        defaultBeanFactory.loadResource(packageName, Component.class);
        defaultBeanFactory.createAllBeans();
        loadBeanFactory(defaultBeanFactory);
        setProxy(defaultBeanFactory);
    }

    public void setProxy(BeanFactory beanFactory){
        aopSupport = new AopSupport();
        aopSupport.loadAspects(packageName);
        aopSupport.loadAdviseds(beanFactory);
        LinkedList<Advised> adviseds = aopSupport.getAdviseds();
        adviseds.forEach(advised -> {
            Object object = new ProxyBean(advised).getNewProxy();
            beanFactory.setBean(advised.getSimpleName(), object);
        });

    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }
}
