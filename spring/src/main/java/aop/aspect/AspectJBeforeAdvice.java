package aop.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhiqiu
 */
public class AspectJBeforeAdvice implements AspectJAdvice {

    private Method method;


    private Object aspect;

    public AspectJBeforeAdvice() {

    }

    public AspectJBeforeAdvice(Method method, Object aspect) {
        this.method = method;
        this.aspect = aspect;
    }

    @Override
    public void invoke(MethodInvocation methodInvocation) {
        try {
            method.invoke(aspect);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        methodInvocation.proceed();
    }

}
