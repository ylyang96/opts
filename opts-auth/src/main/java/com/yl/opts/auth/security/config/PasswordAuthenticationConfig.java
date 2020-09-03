package com.yl.opts.auth.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * @author ylyang
 */
@Configuration
public class PasswordAuthenticationConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
}
