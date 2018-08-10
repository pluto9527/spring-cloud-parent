package com.jcfc.springcloud.fallback;

import com.jcfc.springcloud.entity.Dept;
import com.jcfc.springcloud.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  熔断切面类
 *  1.@HystrixCommand 熔断要给每个服务端接口方法都加上，代码耦合性太强
 *  2.使用SpringAop切面思想，使用通知织入的方式加上失败回调方法
 */
@Component  //必须加入容器中
public class FeignServiceFallBackFactory implements FallbackFactory<FeignService> {
    @Override
    public FeignService create(Throwable cause) {
        //复写调用失败后的回调方法
        return new FeignService() {
            @Override
            public Dept get(long id) {
                return new Dept().setDeptno(id).setDname("来自回调工厂").setDbSource("NULL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
