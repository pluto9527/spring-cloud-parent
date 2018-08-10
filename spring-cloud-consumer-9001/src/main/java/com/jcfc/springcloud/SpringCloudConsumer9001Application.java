package com.jcfc.springcloud;

import com.jcfc.rule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
/**
 * 	如果想给某个服务定制特别的策略,其它服务使用默认策略，使用@RibbonClient
 * 		name指定服务名，configuration指定自定义的策略类
 * 	注意：自定义的策略类不能在@ComponentScan注解扫描的包及子包下，
 * 		否则会被所有的Ribbon客户端共享，不能达到特殊定制效果了
 */
@RibbonClient(name = "PROVIDER", configuration = {MyRibbonRule.class})
public class SpringCloudConsumer9001Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumer9001Application.class, args);
	}
}
