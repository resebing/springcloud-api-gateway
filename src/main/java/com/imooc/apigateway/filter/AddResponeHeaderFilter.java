package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * 实现服务网关的统一返回请求结果后，在返回结果的请求头中加入信息，的过滤器.后置过滤器
 * <p>
 * FilterConstants是Zuul定义的常用常量
 */
@Component
public class AddResponeHeaderFilter extends ZuulFilter {

    /**
     * 定义过滤器的类型为后置
     */
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    /**
     * 设置过滤器的优先级。数字越小越考前
     * 设置过滤器在发送响应过滤器之前
     * 官方推荐写法
     */
    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    /**
     * 设置是否开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 实际处理业务  在返回结果的响应头中加入信息，
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        // 设置响应头的内容
        response.setHeader("x-foo", "x-foo");
        return null;
    }
}
