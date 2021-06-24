package com.kuang;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.printf(jedis.ping());
        jedis.close();
    }
}
