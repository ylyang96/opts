package com.yl.opts.auth.security.provider;

import com.yl.opts.auth.entity.SysUser;
import com.yl.opts.auth.security.token.PasswordAuthenticationToken;
import com.yl.opts.auth.service.AuthSysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author ylyang
 * 认证
 */
@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private AuthSysUserService authSysUserService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordAuthenticationToken passwordAuthenticationToken = (PasswordAuthenticationToken) authentication;
        if (StringUtils.isBlank(passwordAuthenticationToken.getAccount())){
            throw new InsufficientAuthenticationException("帐号不能为空");
        }
        if (StringUtils.isBlank(passwordAuthenticationToken.getPassword())){
            throw new InsufficientAuthenticationException("帐号不能为空");
        }
        SysUser sysUser = authSysUserService.passwordLogin(passwordAuthenticationToken.getAccount(), passwordAuthenticationToken.getPassword());
        passwordAuthenticationToken.setUserId(sysUser.getUserId());
        passwordAuthenticationToken.setUserName(sysUser.getUsername());
        passwordAuthenticationToken.setClientId(1);
        return passwordAuthenticationToken;
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
