package com.jcfc.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)    //链式编程
@ToString
public class Dept implements Serializable {
    private static final long serialVersionUID = 3864786629092030213L;

    private Long 	deptno; // 主键
    private String 	dname; // 部门名称
    private String 	dbSource;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

}
