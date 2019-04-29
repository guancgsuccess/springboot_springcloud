package com.tensquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@EnableCaching
@SpringBootApplication
public class TensquareGatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareGatheringApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}

}
