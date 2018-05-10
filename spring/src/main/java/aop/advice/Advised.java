package aop.advice;

import lombok.Data;

import java.util.LinkedList;

/**
 * 最终的增强方法
 * @author zhiqiu
 */
@Data
public class Advised {

    private Class<?>[] interfaces;

    private String simpleName;

    private Object target;

    private LinkedList<Advisor> advisors = new LinkedList<>();
}
