package com.zlht.pbr.algorithm.management.configuration;


import com.zlht.pbr.algorithm.management.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;


/**
 * @author ziji Wang
 * application configuration
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    public static final String LOGIN_INTERCEPTOR_PATH_PATTERN = "/**/*";
    public static final String LOGIN_PATH_PATTERN = "/login";
    public static final String LOGIN_PATH_PATTERN_DEVELOPER = "/developer/login";
    public static final String LOGIN_PATH_PATTERN_WECHAT = "/wechat/*";
    public static final String REGISTER_PATH_PATTERN = "/users/register";
    public static final String PATH_PATTERN = "/**";
    public static final String LOCALE_LANGUAGE_COOKIE = "language";


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(PATH_PATTERN, config);
        return new CorsFilter(configSource);
    }

    @Bean
    public LoginHandlerInterceptor loginInterceptor() {
        return new LoginHandlerInterceptor();
    }

    /**
     * Cookie
     *
     * @return local resolver
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName(LOCALE_LANGUAGE_COOKIE);
        // set default locale
        localeResolver.setDefaultLocale(Locale.US);
        // set language tag compliant
        localeResolver.setLanguageTagCompliant(false);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns(LOGIN_INTERCEPTOR_PATH_PATTERN)
                .excludePathPatterns(LOGIN_PATH_PATTERN_WECHAT, LOGIN_PATH_PATTERN, LOGIN_PATH_PATTERN_DEVELOPER, REGISTER_PATH_PATTERN,
                        "/swagger-resources/**", "/webjars/**", "/v3/api-docs/**", "/api-docs/**",
                        "/doc.html", "/swagger-ui/**", "*.html", "/ui/**", "/error");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * Turn off suffix-based content negotiation
     *
     * @param configurer configurer
     */
    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    }
}
