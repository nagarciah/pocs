package com.modusoftware.oauth2.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @see http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HashPassword {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        String password = "bgd";
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }

}
