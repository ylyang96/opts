package com.yl.opts.auth.security.token;

import com.yl.opts.auth.security.base.BaseToken;
import lombok.Data;

/**
 * @author ylyang
 */
@Data
public class PasswordAuthenticationToken extends BaseToken {

    private String account;

    private String password;


}
