package com.imooc.apigateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * 实现跨域的资源共享的过滤器
 */
@Configuration
public class CorsConfig {

    /**
     * 实现跨域的资源共享
     * <p>
     * 使用 Spring 的 {@link CorsFilter}（与 {@code org.apache.catalina} 包中同名类区分）。CORS 规范中
     * {@code Access-Control-Allow-Origin: *} 与带 Cookie 的凭证模式不能同时成立；原先用 {@code *} 与
     * {@code allowCredentials(true)} 会导致浏览器拒答，此处对任意来源放开时使用 {@code allowCredentials(false)}。
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(300L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
