package ioc.context;

public interface Context {


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
}
