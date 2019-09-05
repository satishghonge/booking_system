package com.demo.bookingsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
/**
 * @author Satish Ghonge
 *
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
	
	@Value("${booking.imagepath}")
	private String bookingImagePath;

	@Value("${booking.systemPath}")
	private String bookingSystemPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/images/**").addResourceLocations(bookingSystemPath + bookingImagePath)
				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
	}
}
