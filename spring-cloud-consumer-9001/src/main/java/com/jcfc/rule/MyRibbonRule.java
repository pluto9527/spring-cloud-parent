package com.jcfc.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//不能放在@Component扫描包及子包下
@Configuration
public class MyRibbonRule {

    //使用自定义的策略
    @Bean
    public IRule iRule() {
        return new FiveTimeRoundRobinRule();
    }

}
