package com.lautbiru.productRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProductRegistrationApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProductRegistrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductRegistrationApplication.class, args);
	}

	@Value("${hello.world.message : display this if no message available in application.properties}")
	private String name;

	@RequestMapping(value = "/")
	public String hello() {
		logger.info("Hellow Message is -> "+name);
		return name;
	}
}
