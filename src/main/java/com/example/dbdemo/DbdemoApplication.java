package com.example.dbdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableCaching
public class DbdemoApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DbdemoApplication.class, args);
		System.out.println("DbdemoApplication is running");
	}

}
/*
fetching all data from department
SELECT * FROM public.department
ORDER BY department_id ASC
select a.department_id,a.department_name from department a;
public department findAll() {
}
spring data -> spring data jpa, spring data orm, spring data jdbc
* */