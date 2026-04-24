package com.imooc.apigateway.constant;

public interface CookieConstant {

    String TOKEN = "token";

    /** 买家登录后写入的 cookie 名 */
    String OPENID = "openid";

    /**
     * 过期时间单位(秒）
     */
    Integer EXPIRE = 7200;
}
