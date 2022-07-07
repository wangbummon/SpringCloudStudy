package com.test.com.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author az
 * @description 路由网关过滤器
 * @date 2022/7/8 0008
 */
@Component
public class TestFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求request  注意此时不是HttpServletRequest
        ServerHttpRequest request = exchange.getRequest();

        //打印所有参数
        System.out.println(request.getQueryParams());

        List<String> header = request.getHeaders().get("Test");
        if (null != header && header.contains("Hello World!")) {
            return chain.filter(exchange);
        }

        List<String> value = request.getQueryParams().get("test");

        //判断请求中是否包含test参数 且值为1
        if (null != value && value.contains("1")) {
            //如果包含则交给过滤器链下一层执行
            return chain.filter(exchange);
        } else {
            //否则将直接返回 不向下执行
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        // order值越小 表明越先执行 当order值相同的时候 先执行定义了order的过滤器
        return 2;
    }
}
