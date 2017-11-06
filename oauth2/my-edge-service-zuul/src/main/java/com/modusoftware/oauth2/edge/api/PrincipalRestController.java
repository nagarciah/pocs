package com.modusoftware.oauth2.edge.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalRestController {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
