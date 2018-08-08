package com.jcfc.springcloud;

import com.jcfc.springcloud.entity.Dept;
import com.jcfc.springcloud.mapper.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudProvider8001ApplicationTests {

	@Autowired
	DeptMapper deptMapper;

	//由于以来的jar包也是SpringBoot应用，所以测试时会有多个@SpringBootApplication注解，所以会报错
	@Test
	public void contextLoads() {
		List<Dept> list = deptMapper.findAll();
		System.out.println(list);
	}

}
