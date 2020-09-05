package com.yl.opts.auth.service;

import com.yl.opts.auth.entity.SysUser;
import org.springframework.security.authentication.AccountStatusException;

/**
 * @author ylyang
 */
public interface AuthSysUserService {

    /**
     * 密码登录借口
     * @param account
     * @param password
     */
    SysUser passwordLogin(String account, String password) throws AccountStatusException;
}
