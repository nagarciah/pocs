package com.modusoftware.oauth2.resource.api;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("secure")
public class MyController {

    @GetMapping("/greet/{name}")
    Map<String, String> hi(@PathVariable String name, Principal p) {
        return Collections.singletonMap("greeting", "Hello, " + name + " from " + p.getName() + "!");
    }
}
