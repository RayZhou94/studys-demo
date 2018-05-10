package ioc.beans;

import ioc.context.Resource;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiqiu
 */
public class DefaultBeanFactory implements BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<String, BeanDefinition>();

    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    public void loadResource(String packageName, Class<? extends Annotation>... annotationClasses){
        Resource resource = new Resource();
        beanDefinitions.putAll(resource.analyzePackageForClasses(packageName, annotationClasses));
    }

    /**
     * 从容器中取出bean
     * @param name
     * @return
     */
    protected Object getSingleton(String name){
        if (!singletonObjects.containsKey(name)){
            createBean(name);
        }
        return singletonObjects.get(name);
    }

    /**
     * 将bean添加到容器中
     * @param name
     * @param object
     */
    protected void addSingleton(String name, Object object){
        singletonObjects.put(name, object);
    }

    /**
     * 判断是否存在bean
     * @param name
     * @return
     */
    protected boolean containsBean(String name){
        return beanDefinitions.containsKey(name);
    }

    /**
     * 创建bean实例
     * @param name
     * @return
     */
    protected void createBean(String name){
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        try {
            Object object = beanDefinition.getClazz().newInstance();
            addSingleton(name, object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void createAllBeans() {
        beanDefinitions.entrySet().forEach(entry->{
            Object object = null;
            try {
                object = entry.getValue().getClazz().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            addSingleton(entry.getKey(), object);
        });
    }

    @Override
    public <T> T getBean(String name) {
        if (!containsBean(name)){
            return null;
        }
        return (T)getSingleton(name);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    @Override
    public <T> void setBean(String name, T bean) {
        addSingleton(name, bean);
    }
}
