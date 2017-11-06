package com.modusoftware.oauth2.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalRestController {

    @GetMapping("/user")
    Principal getPrincipal(Principal p) {
        return p;
    }
}
