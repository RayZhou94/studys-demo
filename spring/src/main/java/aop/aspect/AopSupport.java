package aop.aspect;

import annotations.After;
import annotations.Aspect;
import annotations.Before;
import annotations.Component;
import aop.advice.Advised;
import aop.advice.Advisor;
import ioc.beans.BeanDefinition;
import ioc.beans.BeanFactory;
import ioc.context.Resource;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiqiu
 */
public class AopSupport {

    private final ConcurrentHashMap<String, BeanDefinition> aspects = new ConcurrentHashMap<>();

    private LinkedList<Advised> adviseds = new LinkedList<>();

    private TargetMethods targetMethods = new TargetMethods();

    public ConcurrentHashMap<String, BeanDefinition> loadAspects(String packageName){
        Resource resource = new Resource();
        ConcurrentHashMap<String, BeanDefinition> definitions = resource.analyzePackageForClasses(packageName, Aspect.class, Component.class);
        aspects.putAll(definitions);
        return aspects;
    }

    public LinkedList<Advised> getAdviseds() {
        return adviseds;
    }

    /**
     * 将所有的切点方法通知出去
     */
    public void loadAdviseds(BeanFactory beanFactory){
        aspects.entrySet().forEach(entry->{
            Class<?> clazz = entry.getValue().getClazz();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method: methods){
                if (isBefore(method)){
                    //拿到before注解上的目标方法
                    Method targetMethod = getBeforeMethod(method);
                    Class<?> targetClazz = targetMethod.getDeclaringClass();
                    Object target = beanFactory.getBean(targetClazz.getSimpleName());
                    Object aspect = beanFactory.getBean(clazz.getSimpleName());
                    putAdvised(target, aspect, method, targetMethod, true, false);
                }else if (isAfter(method)){
                    //拿到After注解上的目标方法
                    Method targetMethod = getAfterMethod(method);
                    Class<?> targetClazz = targetMethod.getDeclaringClass();
                    Object target = beanFactory.getBean(targetClazz.getSimpleName());
                    Object aspect = beanFactory.getBean(clazz.getSimpleName());
                    putAdvised(target, aspect, method, targetMethod, false, true);
                }
            }
        });
    }

    /**
     * 判断方法上的注解是否是before
     * @param method
     * @return
     */
    private boolean isBefore(Method method){
        return method.getAnnotationsByType(Before.class).length > 0;
    }

    /**
     * 判断方法上的注解是否是after
     * @param method
     * @return
     */
    private boolean isAfter(Method method){
        return method.getAnnotationsByType(After.class).length > 0;
    }

    /**
     * 获取方法上的before注解里的被切得方法
     * @param method
     * @return
     */
    private Method getBeforeMethod(Method method){
        Before[] befores = method.getAnnotationsByType(Before.class);
        return targetMethods.getMethodByPointcut(befores[0].value());
    }

    /**
     * 获取方法上的after注解里的被切得方法
     * @param method
     * @return
     */
    private Method getAfterMethod(Method method){
        After[] afters = method.getAnnotationsByType(After.class);
        return targetMethods.getMethodByPointcut(afters[0].value());
    }

    private Advised getAdvised(Class<?> clazz){
        for (Advised advised: adviseds){
            if (advised.getTarget().getClass() == clazz) {
                return advised;
            }
        }
        return null;
    }

    private void putAdvised(Object target, Object aspect, Method method, Method targetMethod, boolean isBefore, boolean isAfter){
        Advised advised = getAdvised(target.getClass());
        if (advised == null){
            advised = new Advised();
            adviseds.add(advised);
        }
        advised.setTarget(target);
        advised.setSimpleName(target.getClass().getSimpleName());
        advised.setInterfaces(target.getClass().getInterfaces());
        Advisor advisor = new Advisor();
        advisor.setBefore(isBefore);
        advisor.setAfter(isAfter);
        advisor.setMethod(method);
        advisor.setTargetMethod(targetMethod);
        advisor.setTarget(target);
        advisor.setAspect(aspect);
        advised.getAdvisors().add(advisor);
    }
}
