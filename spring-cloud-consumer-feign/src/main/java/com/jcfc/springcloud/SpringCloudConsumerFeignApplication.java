package com.jcfc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//Feign是面向接口编程，类似于webService
@EnableFeignClients(basePackages = {"com.jcfc.springcloud"})  //指定feign接口所在的包
public class SpringCloudConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumerFeignApplication.class, args);
	}
}
