package com.imooc.apigateway.constant;

/**
 * 网关中使用的 Cookie 名称与过期等常量
 */
public interface CookieConstant {

    String TOKEN = "token";

    String OPENID = "openid";

    /**
     * 过期时间单位(秒)
     */
    int EXPIRE = 7200;
}
