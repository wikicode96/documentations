package com.github.wikicode96.spring.security.db.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enable-method-security")
@PreAuthorize("denyAll()")
public class EnableMethodSecurityController {

    @GetMapping("/hello-basic")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloPublic() {
        return "Hello basic role";
    }

    @GetMapping("/hello-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloSecured() {
        return "Hello admin role";
    }
}
