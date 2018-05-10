package ioc.beans;

public interface BeanFactory {

    /**
     * 通过bean名称获取bean
     *
     * @param name
     * @return
     */
    <T> T getBean(String name);

    /**
     * 通过类类型
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> clazz);

    /**
     * 设置新的bean
     * @param bean
     * @param <T>
     * @return
     */
    <T> void setBean(String name, T bean);
}
