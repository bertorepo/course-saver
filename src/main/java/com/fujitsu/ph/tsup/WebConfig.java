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
 * @author WS) J.Macabudbud
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * <pre>
	 * Configure content negotiation options.
	 * </pre>
	 * 
	 * @param configurer ContentNegotiationConfigurer Object
	 * @return void
	 */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer
    		.ignoreAcceptHeader(false)
            .defaultContentType(MediaType.TEXT_HTML)
            .mediaType("html", MediaType.TEXT_HTML)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("json", MediaType.APPLICATION_JSON);
    }
    
	/**
	 * <pre>
	 * Configure simple automated controllers pre-configured with the response status code 
	 * and/or a view to render the response body.
	 * </pre>
	 * 
	 * @param registry ViewControllerRegistry Object
	 * @return void
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboard").setViewName("Dashboard");
    }
}
