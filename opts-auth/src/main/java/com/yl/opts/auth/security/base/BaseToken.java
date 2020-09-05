package com.yl.opts.auth.security.base;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author ylyang
 */

public class BaseToken extends AbstractAuthenticationToken {


    private Integer userId;

    private String userName;

    /**
     * 认证方式
     */
    private Integer clientId;


    public BaseToken() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
