package ioc.context;

import ioc.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiqiu
 */
public class Resource {

    private static final int initialCapacity = 0;

    private static final String FILE = "file";

    private static final String SUFFIX = ".class";

    public ConcurrentHashMap<String, BeanDefinition> analyzePackageForClasses(String packageName,
                                                                              Class<? extends Annotation>... annotationClasses) {
        final ConcurrentHashMap<String, BeanDefinition> definitions = new ConcurrentHashMap<>();
        //拿到包下的枚举资源文件
        Enumeration<URL> enumeration = analyzePackageForDir(packageName);
        if (enumeration.hasMoreElements()){
            URL url = enumeration.nextElement();
            //获取资源文件的文件路径
            String filePath = getFilePath(url);
            //通过文件路径拿到所有包括自己在内的文件
            Set<File> sets = getChildFile(filePath);
            for (File file: sets){
                //通过文件与其包名，拿到class文件名称
                String className = getClassName(file, packageName);
                try {
                    //拿到通过class文件名称，拿到其类类型
                    Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
                    //如果该类类型上有annotationClass注解，就加入容器
                    for (Class<? extends Annotation>annotationClass: annotationClasses){
                        if (clazz.isAnnotationPresent(annotationClass)){
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            beanDefinition.setName(clazz.getName());
                            beanDefinition.setSimpleName(clazz.getSimpleName());
                            definitions.put(beanDefinition.getSimpleName(), beanDefinition);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.err.println("analyzePackageForClasses error");
                }
            }
        }
        return definitions;
    }

    private Enumeration<URL> analyzePackageForDir(String packageName) {
        //get package dir name
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> enumerations = null;
        try {
            enumerations = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("analyzePackageForDir error");
        }
        return enumerations;
    }

    private String getFilePath(URL url) {
        //获取资源文件的物理路径
        String protocol = url.getProtocol();
        try {
            if (FILE.equals(protocol)) {
                return URLDecoder.decode(url.getFile(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.err.println("getFilePath error");
        }
        return null;
    }

    /**循环获取文件路径下所有文件，包括自己
     *
     * @param filePath
     * @return
     */
    private Set<File> getChildFile(String filePath){
        Set<File> fileSets = new HashSet<>();
        File file = new File(filePath);
        if (!file.exists()){
            return new HashSet<>(initialCapacity);
        }
        if (!file.isDirectory()){
            fileSets.add(file);
        }else {
            File[] files = file.listFiles();
            for (File file1: files){
                fileSets.addAll(getChildFile(file1.getPath()));
            }
        }
        return fileSets;
    }

    /**
     * 包名、文件属性拿到class的全名称
     * @param file
     * @return
     */
    private String getClassName(File file, String packageName){
        // 如果是java类文件 去掉后面的.class 只留下类名和包名
        if (file.getName().endsWith(SUFFIX)){
            String packagePath = packageName.replace(".", "/");
            String filePath = file.getPath();
            int index = filePath.indexOf(packagePath);
            String classPath = filePath.substring(index, filePath.length()- 6);
            String className = classPath.replace("/", ".");
            return className;
        }
        return null;
    }
}
