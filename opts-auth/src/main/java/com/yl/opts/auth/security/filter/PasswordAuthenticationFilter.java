package com.yl.opts.auth.security.filter;

import com.alibaba.nacos.common.utils.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ylyang
 */
public class PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public PasswordAuthenticationFilter(String filterProcessesUrl){
        super(new AntPathRequestMatcher(filterProcessesUrl, HttpMethod.POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.equals(request.getMethod())){
            throw new AuthenticationServiceException("method is error");
        }
        //开始获取参数
        return null;
    }
}
