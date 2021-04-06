package com.kuang.springboot09task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("你好啊");
        message.setText("真的好啊");
        message.setTo("15273128925@163.com");
        message.setFrom("1440654250@qq.com");
        javaMailSender.send(message);
    }

    @Test
    void contextLoads1() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();//复杂邮件格式
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);//用helper来设置邮件相关信息
        //正文
        helper.setSubject("你好啊");
        helper.setText("<p style='color:red'>你真的好啊</p>",true);
        //附件
        helper.addAttachment("tips.docx",new File("C:\\Users\\FlameZ\\Desktop\\tips.docx"));

        helper.setTo("15273128925@163.com");
        helper.setFrom("1440654250@qq.com");

        javaMailSender.send(mimeMessage);
    }


}
