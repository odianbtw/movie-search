package com.odian.moviesearch.core.application.config;


import com.odian.moviesearch.api.util.FilmRequestCriteriaArgumentResolver;
import com.odian.moviesearch.api.util.KeywordRequestCriteriaArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new KeywordRequestCriteriaArgumentResolver());
        resolvers.add(new FilmRequestCriteriaArgumentResolver());
    }
}
