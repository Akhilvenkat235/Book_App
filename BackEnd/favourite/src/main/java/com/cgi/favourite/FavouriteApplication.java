package com.cgi.favourite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.favourite.jwtfilter.FavouriteJwtfilter;

@SpringBootApplication
public class FavouriteApplication {
	

@Bean
public FilterRegistrationBean jwtFilter() {
final FilterRegistrationBean  registrationBean=new FilterRegistrationBean();
registrationBean.setFilter(new FavouriteJwtfilter());
registrationBean.addUrlPatterns("/api2323/*");
return registrationBean;
}

	public static void main(String[] args) {
		SpringApplication.run(FavouriteApplication.class, args);
	}

}
