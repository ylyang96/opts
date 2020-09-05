package com.opts.auth;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ylyang
 */
public class PasswordEncoderFactoriesTest {


    @Test
    public void test001(){
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = "asdfa1231";
        System.out.println(delegatingPasswordEncoder.encode(password));
        System.out.println(delegatingPasswordEncoder.encode(password));
    }
}
