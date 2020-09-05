package com.yl.opts.auth.security.base;

import com.alibaba.fastjson.JSONObject;
import com.yl.opts.common.core.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ylyang
 * 成功处理类
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        BaseToken baseToken = (BaseToken) authentication;
        String token = JwtUtils.createToken(baseToken.getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("code", 1000);
        response.getWriter().write(jsonObject.toString());
    }
}
