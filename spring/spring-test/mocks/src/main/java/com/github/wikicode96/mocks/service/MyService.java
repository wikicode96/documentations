package com.github.wikicode96.mocks.service;

import com.github.wikicode96.mocks.dependency.MyDependency;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyDependency dependency;

    public MyService(MyDependency dependency) {
        this.dependency = dependency;
    }

    public String process() {
        return dependency.doSomething();
    }
}
