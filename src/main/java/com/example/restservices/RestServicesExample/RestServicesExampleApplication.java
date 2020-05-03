package com.example.restservices.RestServicesExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestServicesExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServicesExampleApplication.class, args);
	}

	/*Use this method is reading locale from Request Header*/
	/*@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}*/

	/*We can use one of these two methods according to our need*/

	/*Use below method in case of reading locale from LocaleContext*/
	@Bean
	public LocaleResolver acceptHeaderLocaleResolver(){
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/*	This method can be replaces by adding below in application.properties file
		*spring.messages.basename = messages
	*/
	/*@Bean
	public ResourceBundleMessageSource bundleMessageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
}
