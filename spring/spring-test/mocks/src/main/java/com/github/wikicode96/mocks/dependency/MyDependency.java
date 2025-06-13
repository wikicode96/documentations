package com.github.wikicode96.mocks.dependency;

import org.springframework.stereotype.Component;

@Component
public class MyDependency {
    public String doSomething() {
        return "Hello form MyDependency";
    }
}
