package com.imooc.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.apigateway.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 实现限流的前置过滤器，放在过滤器的最前面。基于令牌桶算法实现
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    /**
     * 令牌桶算法，google的guava中有实现。创建令牌桶，设置每秒钟放入多少个令牌。也就是每秒钟限制多少流量访问
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    /**
     * 定义过滤器的类型为前置
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 设置最高优先级。组件中最高优先级是-3。我们的限流要设置比-3更高
     */
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    /**
     * 设置是否开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 实际处理业务，限流
     */
    @Override
    public Object run() {
        // 尝试获取令牌。如果没有取到令牌，则返回401。或者其他状态码。或者抛出异常
        if (!RATE_LIMITER.tryAcquire()) {
//            // 获取当前上下文的内容
//            RequestContext requestContext = RequestContext.getCurrentContext();
//            // 设置Zuul响应为false。表示不通过
//            requestContext.setSendZuulResponse(false);
//            // 设置响应的状态码
////            requestContext.setResponseStatusCode(401);
//            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            throw new RateLimiterException();
        }
        return null;
    }
}
