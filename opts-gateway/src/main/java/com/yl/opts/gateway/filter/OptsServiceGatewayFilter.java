package com.yl.opts.gateway.filter;

import com.yl.opts.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @author ylyang
 */
@Slf4j
@Component
@AllArgsConstructor
public class OptsServiceGatewayFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            //获取request
            ServerHttpRequest request = exchange.getRequest();
            //判断如果是登录接口 就直接return
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)){
                return chain.filter(exchange);
            }

            return chain.filter(exchange);
        };
    }
}
