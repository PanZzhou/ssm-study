package com.kuang.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KuangRule {
    @Bean
    public KuangRandomRule getKuangRandomRule(){
        return new KuangRandomRule();
    }
}
