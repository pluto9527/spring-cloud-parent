package com.jcfc.springcloud.service;

import java.util.List;

import com.jcfc.springcloud.entity.Dept;

public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
