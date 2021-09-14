package dev.patika.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomConfiguration implements WebMvcConfigurer {

    @Bean
    public ClientLogInterceptor getInterceptor(){
        return new ClientLogInterceptor();
    }

    /*
        ** , It checks every url on requests, under project
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
    }
}
