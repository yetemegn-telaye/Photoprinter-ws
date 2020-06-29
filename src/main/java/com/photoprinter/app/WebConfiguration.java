package com.photoprinter.app;


//import org.h2.server.web.WebServlet;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
					.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	}
	
	/*
	 * @Bean ServletRegistrationBean h2servletRegistration(){
	 * ServletRegistrationBean registrationBean = new ServletRegistrationBean( new
	 * WebServlet()); registrationBean.addUrlMappings("/console/*"); return
	 * registrationBean; }
	 */
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
}
