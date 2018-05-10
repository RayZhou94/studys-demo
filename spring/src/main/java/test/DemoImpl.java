package test;

import annotations.Component;

@Component
public class DemoImpl implements Demo {

    private int id;

    @Override
    public void printer(){
        System.out.println("printer");
    }

    @Override
    public void print() {
        System.out.println("print");
    }
}
