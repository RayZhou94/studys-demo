package aop.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhiqiu
 */
public class AspectJAfterAdvice implements AspectJAdvice {

    private Method method;


    private Object aspect;

    public AspectJAfterAdvice() {

    }

    public AspectJAfterAdvice(Method method, Object aspect) {
        this.method = method;
        this.aspect = aspect;
    }
    @Override
    public void invoke(MethodInvocation methodInvocation) {
        methodInvocation.proceed();
        try {
            method.invoke(aspect);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
