package com.johnbryce.CouponSystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSystem2Application {

	public static void main(String[] args) {
		SpringApplication.run(CouponSystem2Application.class, args);
		System.out.println("IOC WAS LOADED!");
	}

}
