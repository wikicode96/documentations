package com.github.wikicode96.spring.security.db.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enable-method-security")
@PreAuthorize("denyAll()")
public class EnableMethodSecurityController {

    @GetMapping("/hello-read")
    @PreAuthorize("hasAuthority('READ')")
    public String helloPublic() {
        return "Hello read";
    }

    @GetMapping("/hello-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloSecured() {
        return "Hello admin role";
    }
}
