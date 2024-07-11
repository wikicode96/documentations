package com.github.wikicode96.spring.security.db.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize-http-requests")
public class AuthorizeHttpRequestsController {

    @GetMapping("/hello-public")
    public String helloPublic() {
        return "Hello public";
    }

    @GetMapping("/hello-secured")
    public String helloSecured() {
        return "Hello secured";
    }
}
