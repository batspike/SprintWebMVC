package com.samcancode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.samcancode"})
/* The interface WebMvcConfigurer defines callback methods to customize the Java-based configuration
for Spring MVC enabled by using @EnableWebMvc. Although there can be more than one Java-based
configuration class in a Spring application, only one is allowed to be annotated with @EnableWebMvc */
public class WebConfig implements WebMvcConfigurer 
{
	@Override // static resouces
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/").setCachePeriod(31556926);
	}
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views");
		resolver.setSuffix(".jspx");
		resolver.setRequestContextAttribute("requestContext");
		return resolver;
	}
	
	@Override // <mvc:view-controller .../>
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("singers/list");
	}
	
	@Override // <mvc:default-servlet-handler/>
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
