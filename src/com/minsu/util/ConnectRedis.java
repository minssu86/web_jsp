package com.minsu.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConnectRedis {

    String host = "127.0.0.1";
    int port = 6379;
    int timeout = 3000;
    int db = 0;

    private final JedisPoolConfig jedisPoolConfig;


    public ConnectRedis(){
        this.jedisPoolConfig = new JedisPoolConfig();

    }

    public void rediTest(){
        JedisPool jedisPool;
        Jedis jedis;
        jedisPool = new JedisPool();
        jedis = new Jedis();
        System.out.println("연결 성공 ? " + jedis.isConnected());

        jedis.set("key1", "1");
        jedis.set("key2", "6");

        if( jedis != null ){
            jedis.close();
        }
        if( jedisPool != null ){
            jedisPool.close();
        }

    }

}
