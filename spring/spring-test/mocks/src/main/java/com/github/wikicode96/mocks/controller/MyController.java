package com.github.wikicode96.mocks.controller;

import com.github.wikicode96.mocks.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return service.process();
    }
}
