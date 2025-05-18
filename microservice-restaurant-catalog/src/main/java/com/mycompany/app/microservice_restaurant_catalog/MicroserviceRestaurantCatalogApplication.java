package com.mycompany.app.microservice_restaurant_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceRestaurantCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRestaurantCatalogApplication.class, args);
	}

}
