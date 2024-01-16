package com.example.dbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableCaching
public class DbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbdemoApplication.class, args);
		System.out.println("DbdemoApplication is running");
	}

}
