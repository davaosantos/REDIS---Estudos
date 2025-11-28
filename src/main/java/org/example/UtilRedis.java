package org.example;

import redis.clients.jedis.Jedis;

public class UtilRedis {

    private static Jedis jedisInstance = new Jedis();

    public static Jedis getJedisInstance(){
        return jedisInstance;
    }
}
