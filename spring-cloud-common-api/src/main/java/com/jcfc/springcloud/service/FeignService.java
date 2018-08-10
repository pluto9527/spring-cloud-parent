package com.jcfc.springcloud.service;

import com.jcfc.springcloud.entity.Dept;
import com.jcfc.springcloud.fallback.FeignServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//面向接口的负载均衡 value指定服务名，底层还是通过ribbon做的负载均衡
//@FeignClient(value = "PROVIDER")
//第二个参数指定回调的切面类，当调用失败抛异常时，会去容器中寻找回工厂，调用回调方法
@FeignClient(value = "PROVIDER", fallbackFactory = FeignServiceFallBackFactory.class)
public interface FeignService {

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);

}
