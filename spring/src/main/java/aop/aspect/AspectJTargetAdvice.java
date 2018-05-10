package aop.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhiqiu
 */
public class AspectJTargetAdvice implements AspectJAdvice {

    private Method targetMethod;

    private Object target;

    public AspectJTargetAdvice() {

    }

    public AspectJTargetAdvice(Method targetMethod, Object target) {
        this.targetMethod = targetMethod;
        this.target = target;
    }
    @Override
    public void invoke(MethodInvocation methodInvocation) {
        try {
            targetMethod.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        methodInvocation.proceed();
    }

}
