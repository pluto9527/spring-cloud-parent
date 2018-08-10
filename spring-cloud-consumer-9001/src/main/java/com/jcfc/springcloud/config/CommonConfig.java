package com.jcfc.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    @Bean
    @LoadBalanced   //客户端（服务消费者）做负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //修改负载均衡算法
    @Bean
    public IRule iRule() {
//        return new RandomRule();//随机策略，默认是轮询
        return new RoundRobinRule();
    }

}
