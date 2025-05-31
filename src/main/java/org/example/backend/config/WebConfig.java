package org.example.backend.config;

import org.example.backend.utils.CurrentUserIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CurrentUserIdResolver currentUserIdResolver;

    @Autowired
    public WebConfig(CurrentUserIdResolver currentUserIdResolver) {
        this.currentUserIdResolver = currentUserIdResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserIdResolver);
    }
}
