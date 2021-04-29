package com.kuang.springcloud.service;

import com.kuang.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//降级
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            public Dept queryById(Long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务暂时访问不了")
                        .setDb_source("没有数据库");
            }

            public List<Dept> queryAll() {
                return null;
            }

            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
