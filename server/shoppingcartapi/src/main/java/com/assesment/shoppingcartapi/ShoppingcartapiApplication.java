package com.assesment.shoppingcartapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ShoppingcartapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartapiApplication.class, args);
	}
	
	@RequestMapping(value = "/")
	public String hello() {
		return "Hello!";
	}

}
