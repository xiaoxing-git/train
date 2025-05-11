package com.xiaoxing.filter;


import cn.hutool.http.HttpStatus;
import com.xiaoxing.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * web 过滤器
 */
@Slf4j
@Component
public class WebFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // 放行路径
        if (path.contains("/admin")
                ||path.contains("/member/member/login")
                ||path.contains("/member/member/sendCode")){
            log.info("放行:{}",path);
            return chain.filter(exchange);
        }
        // 获取 header 的 token 参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        // 校验 token
        if (token==null||token.isBlank()){
            log.info("token is null");
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.HTTP_UNAUTHORIZED));
            return exchange.getResponse().setComplete();
        }
        boolean isTrue = JWTUtil.validate(token);
        if (!isTrue){
            log.info("token 校验失败");
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.HTTP_UNAUTHORIZED));
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
