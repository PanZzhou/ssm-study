package com.kuang.utils;

import org.junit.Test;

import java.util.UUID;

public class IdUtils {
    /*
    UUID（Universally Unique Identifier）：通用唯一识别码，是一种软件建构的标准。
    UUID 目的是让分布式系统中的所有元素，都能有唯一的辨识信息，而不需要通过中央控制端来做辨识信息的指定。
    UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
    UUID由以下几部分组合：(1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。
(2）时钟序列。 （3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
UUID的唯一缺陷在于生成的结果串会比较长。
*/
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IdUtils.getId());
    }
}
