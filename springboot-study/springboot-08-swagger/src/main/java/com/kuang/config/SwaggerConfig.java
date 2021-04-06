package com.kuang.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration  //swagger是第三方的，需要自己配置
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    //配置了swagger的docket实例
    @Bean
    public Docket docket(Environment environment){
        //作者的相关信息
        Contact contact = new Contact("PanZzhou", "https://github.com/PanZzhou/ssm-study", "12345678@163.com");
        //Api的香瓜信息
        ApiInfo apiInfo = new ApiInfo(
                "PanZzhou的swagger Api文档",
                "啦啦啦啦啦啦啦",
                "v1.0",
                "https://www.bilibili.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

        Profiles profiles = Profiles.of("dev","test"); //生产环境是dev和test
        boolean flag = environment.acceptsProfiles(profiles);//获取当前运行环境是不是符合dev和test环境。


        return new Docket(DocumentationType.SWAGGER_2)
                //设置Api的相关信息。
                .apiInfo(apiInfo)
                //是否启动swagger，如果时false，则不能在浏览器中使用swagger
                .enable(flag)
                //select、apis和build要一起用，设置Swagger要生成Api文档信息的是哪些方法
                .select()
                /*
                RequestHandlerSelectors：配置要扫面接口的方式
                basePackage：指定要扫描的包
                any: 全部
                none: 不扫描
                withClassAnnotation: 扫描类上的特定注解，有这类注解就添加到Api中
                withMethodAnnotation: 扫描方法上的特定注解，有这类注解就添加到Api中
                 */
                .apis(RequestHandlerSelectors.basePackage("com.kuang.controller"))
//                .paths(PathSelectors.ant("/kuang/**")) //过滤什么路径
                .build();
    }

    @Bean
    public Docket docketA(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docketB(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docketC(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }
}
