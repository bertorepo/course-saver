package com.fujitsu.ph.tsup;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * It is a Class web configuration and controller registry
 * </pre>
 * 
 * @version 0.01
 * @author j.macabudbud
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer
    		.ignoreAcceptHeader(false)
            .defaultContentType(MediaType.TEXT_HTML)
            .mediaType("html", MediaType.TEXT_HTML)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboard").setViewName("Dashboard");
    }
}
