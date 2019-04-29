package com.tensquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TensquareFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareFriendApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}

}
