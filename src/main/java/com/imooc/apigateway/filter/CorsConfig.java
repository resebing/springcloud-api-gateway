package com.imooc.apigateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * 实现跨域的资源共享的过滤器
 */
@Configuration
public class CorsConfig {

    /**
     * 实现跨域的资源共享
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        // 设置跨域的配置
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Spring 不允许在 allowCredentials(true) 的同时使用通配符 allowedOrigins("*")，会抛异常。
        // 通配源且允许带凭证时，请在生产环境改为具体域名列表，并将此项设为 true。
        corsConfiguration.setAllowCredentials(false);
        // 设置要支持哪些域名或者哪些域名下的接口  *表示支持所有
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        // 设置域名跨域的头部信息
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        // 设置支持哪些请求方式 get/post/...
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        // 设置同一个接口的访问请求，如果第一次访问后，后续多久内不需要进行跨域检查.时间单位/s
        corsConfiguration.setMaxAge(300L);
        // path是域名 /**表示所有
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
