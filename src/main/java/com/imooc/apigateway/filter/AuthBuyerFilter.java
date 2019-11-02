package com.imooc.apigateway.filter;

import com.imooc.apigateway.constant.RedisConstant;
import com.imooc.apigateway.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 买家的过滤器
 */
public class AuthBuyerFilter extends ZuulFilter {

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
     * 设置是否应该拦截
     */
    @Override
    public boolean shouldFilter() {
        // 获取当前上下文的内容
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取Request
        HttpServletRequest request = requestContext.getRequest();
        // 如果是这个url则需要进行拦截
        if ("/order/order/createOrder".equals(request.getRequestURI())) {
            return true;
        }
        return false;
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 实际处理鉴定权限的业务
     */
    @Override
    public Object run() {
        // 获取当前上下文的内容
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取Request
        HttpServletRequest request = requestContext.getRequest();
        /**
         * 要限制，必须用户先进入Api网关服务，然后进入登陆服务，登陆完成后转发到创建订单或者完结订单服务
         * 做限制，必须要有区分的标志
         * /order/createOrder  创建订单 只能买家访问（cookie有openid)
         * /order/finish  订单完结 只能卖家访问（cookie有token，并且对应的redis里有值)
         * /product/list  商品列表 都可以访问
         */
        // 买家到业务逻辑
        // 1、买家调用创建订单服务之前，需要先登陆，登陆后将登陆信息写入到cookie中。
        // 2、如果买家创建订单时，发现没有cookie存在。则设置无权限访问
        // 判断买家登陆的openid在cookie中是否存在 。这里应该填写的URL应该加上服务名称。只有填写上服务名称才会启用过滤器
        Cookie cookie = CookieUtil.getCookie(request, "openid");
        if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
            // 设置Zuul没有访问权限
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
