package com.ying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class ShoppingCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCommonApplication.class, args);
	}

}
