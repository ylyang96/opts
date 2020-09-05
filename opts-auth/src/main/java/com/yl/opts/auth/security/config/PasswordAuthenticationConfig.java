package com.yl.opts.auth.security.config;

import com.yl.opts.auth.security.base.MyAuthenticationFailureHandler;
import com.yl.opts.auth.security.base.MyAuthenticationSuccessHandler;
import com.yl.opts.auth.security.filter.PasswordAuthenticationFilter;
import com.yl.opts.auth.security.provider.PasswordAuthenticationProvider;
import com.yl.opts.common.core.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ylyang
 */
@Configuration
public class PasswordAuthenticationConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private PasswordAuthenticationProvider passwordAuthenticationProvider;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        PasswordAuthenticationFilter passwordAuthenticationFilter = new PasswordAuthenticationFilter(SecurityConstants.PASSWORD_LOGIN_URL);
        passwordAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        passwordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        passwordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        builder.authenticationProvider(passwordAuthenticationProvider)
                .addFilterAfter(passwordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
