package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 实现服务网关的统一权限校验的过滤器.前置过滤器
 * <p>
 * FilterConstants是Zuul定义的常用常量
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 定义过滤器的类型为前置
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 设置过滤器的优先级。数字越小越考前
     * 设置过滤器在前置装饰过滤器的前面
     * 官方推荐写法
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 设置是否开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 实际处理鉴定权限的业务
     */
    @Override
    public Object run() {
        // 获取当前上下文的内容
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取Request
        HttpServletRequest request = requestContext.getRequest();
        // 校验信息。可以从参数中获取，也可以从Cookie或者Header中获取
        String token = request.getParameter("token");
//        if (StringUtils.isBlank(token)) {
//            // 设置Zuul响应为false。表示不通过
//            requestContext.setSendZuulResponse(false);
//            // 设置响应的状态码
////            requestContext.setResponseStatusCode(401);
//            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//        }
        return null;
    }
}
