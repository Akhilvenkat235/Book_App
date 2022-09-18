package com.cgi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.user.jwtFilter.Jwtfilter;

@SpringBootApplication
public class UserApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
	final FilterRegistrationBean  registrationBean=new FilterRegistrationBean();
	registrationBean.setFilter(new Jwtfilter());
	registrationBean.addUrlPatterns("/user/*");
	return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
