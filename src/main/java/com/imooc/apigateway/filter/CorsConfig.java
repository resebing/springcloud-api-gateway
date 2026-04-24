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
     * <p>使用 Spring 的 {@link CorsFilter} 并关联 {@link UrlBasedCorsConfigurationSource}，否则
     * CORS 规则不会应用。凭据为 true 时与 {@code allowedOrigins: *} 不兼容，故此处不携带凭据的开放跨域
     * 使用通配；若需 cookie 跨域，请配置具体源列表并开启 {@code setAllowCredentials(true)}。
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
        corsConfiguration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
        corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        corsConfiguration.setMaxAge(300L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
