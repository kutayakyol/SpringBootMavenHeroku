package com.kutay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ToDoListSpringMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListSpringMavenApplication.class, args);
	}
}

@Configuration
class DefaultView extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController( "/" ).setViewName( "forward:/home.html" );
		registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
		super.addViewControllers( registry );
	}
}
