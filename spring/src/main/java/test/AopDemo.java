package test;

import annotations.After;
import annotations.Aspect;
import annotations.Before;
import annotations.Component;

@Component
@Aspect
public class AopDemo {

    @Before(value = "test.DemoImpl.Print(..)")
    public void beforePrint(){
        System.out.println("before print");
    }

    @After(value = "test.DemoImpl.Print(..)")
    public void afterPrint(){
        System.out.println("after print");
    }
    @Before(value = "test.DemoImpl.printer(..)")
    public void beforePrinter(){
        System.out.println("before printer");
    }

    @After(value = "test.DemoImpl.printer(..)")
    public void afterPrinter(){
        System.out.println("after printer");
    }



}
