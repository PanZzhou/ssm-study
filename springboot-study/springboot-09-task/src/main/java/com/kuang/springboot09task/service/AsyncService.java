package com.kuang.springboot09task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {


    @Async  //表示这个方法时异步任务方法，其他方法调用这个方法时不用等这个方法执行完。
    public void doService(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
