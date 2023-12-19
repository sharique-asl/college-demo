package com.example.dbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbdemoApplication {

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