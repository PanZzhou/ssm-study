package com.kuang.springcloud.service;

import com.kuang.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "springcloud-provider-dept", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);
    @GetMapping("/dept/list")
    List<Dept> queryAll();
    @PostMapping("/dept/add")
    boolean addDept(Dept dept);
}
