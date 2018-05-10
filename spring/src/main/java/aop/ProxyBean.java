package aop;

import aop.advice.AdviceUtil;
import aop.advice.Advised;
import aop.advice.Advisor;
import aop.aspect.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyBean implements InvocationHandler {

    private Advised advised;

    private Object proxy;

    public ProxyBean(Advised advised){
        this.advised = advised;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        List<Advisor> chain = AdviceUtil.getAdvisor(method, advised, proxy);
        MethodInvocation methodInvocation = new MethodInvocation(chain);
        methodInvocation.proceed();
        return null;
    }

    public Object getNewProxy(){
        if (proxy == null){
            proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getInterfaces(), this);
        }
        return proxy;
    }
}
