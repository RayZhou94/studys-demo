package com.example.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo2Impl implements Demo {
    @Override
    public void print() {
        log.info(this.getClass().getSimpleName());
    }
}
