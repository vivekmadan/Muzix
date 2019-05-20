package com.ibmcapsule.zuulservice.config;

import com.ibmcapsule.zuulservice.filter.*;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BeanConfiguration {

  @Bean
  public FilterRegistrationBean jwtFilter(){
    final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new JwtFilter());
    registrationBean.addUrlPatterns("/usertrackservice/api/v1/usertrackservice/user/*");
    return registrationBean;
  }

  @Bean
  public ErrorFilter errorFilter(){
    return new ErrorFilter();
  }

  @Bean
  public PostFilter postFilter(){
    return new PostFilter();
  }

  @Bean
  public PreFilter preFilter(){
    return new PreFilter();
  }

  @Bean
  public RouterFilter routerFilter(){
    return new RouterFilter();
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("*", config);
    return new CorsFilter(source);
  }
}
