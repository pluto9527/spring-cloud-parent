package com.jcfc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker	//开启对hystrix服务熔断的支持
public class SpringCloudProviderHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProviderHystrixApplication.class, args);
	}
}
