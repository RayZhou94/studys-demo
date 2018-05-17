package test;

import aop.aspect.AopSupport;
import ioc.context.AopContext;
import ioc.context.DefaultContext;

/**
 * @author zhiqiu
 */
public class Test {
    //public static void main(String[] args) {
    //    DefaultContext defaultContext = new DefaultContext("test");
    //    Demo server = defaultContext.getBean("Demo");
    //    server.printer();
    //}

    public static void testAop(){
        AopContext aopContext = new AopContext("test");
        Demo demo = aopContext.getBean("DemoImpl");
        demo.printer();
        demo.print();
    }
    public static void main(String[] args) {
        testAop();
    }
}
