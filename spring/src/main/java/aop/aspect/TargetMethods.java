package aop.aspect;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TargetMethods {

    public Set<Method> methodsFromPointcut(String pointcut){
        Set<Method> methods = new HashSet<>();
        methods.add(getMethodByPointcut(pointcut));
        return methods;
    }

    public Method getMethodByPointcut(String pointcut){
        String var = "(.+)\\.(\\w+)\\(\\.\\.\\)$";
        Pattern pattern = Pattern.compile(var);
        Matcher matcher = pattern.matcher(pointcut);
        String methodName = null;
        String classPath = null;
        if (matcher.find()){
            classPath = matcher.group(1);
            methodName = matcher.group(2);
        }
        Class<?> clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(classPath);
        } catch (ClassNotFoundException e) {
            System.err.println("getMethodByPointcut error");
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method: methods){
            if (method.getName().equalsIgnoreCase(methodName)){
                return method;
            }
        }
        return null;
    }
}
