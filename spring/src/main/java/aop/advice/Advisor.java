package aop.advice;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author zhiqiu
 */
@Data
public class Advisor {

    private boolean isBefore;

    private boolean isAfter;

    private Method method;

    private Method targetMethod;

    private Object target;

    private Object aspect;
}
