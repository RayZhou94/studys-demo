package ioc.beans;

import lombok.Data;

/**
 * @author zhiqiu
 */
@Data
public class BeanDefinition {

    private Class<?> clazz;

    private String simpleName;

    private String name;

}
