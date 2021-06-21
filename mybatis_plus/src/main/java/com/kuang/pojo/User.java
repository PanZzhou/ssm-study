package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version//乐观锁version注解
    private Integer version;
    @TableLogic
    private Integer deleted;
}
