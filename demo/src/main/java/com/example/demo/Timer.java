package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Timer {

    @Autowired
    private Demo demo;

    @PostConstruct
    void test(){
        demo.print();
    }
}
