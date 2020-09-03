package com.yl.opts.auth.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author ylyang
 * 认证
 */
@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
