package com.study.redis.main;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Jedis操作事务
 */
public class JedisMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        jedis.set("key_a", "value");
        // 实现乐观锁
        jedis.watch("key_a");
        // fastjson
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("redis", "transaction");
        jsonObject.put("mongoDb", "transaction");
        String jsonString = jsonObject.toJSONString();
        // 开启事务
        Transaction transaction = jedis.multi();
        try {
            transaction.set("key_b", jsonString);
            transaction.set("key_c", jsonString);
            // 执行事务
            transaction.exec();
        } catch (Exception e) {
            // 放弃事务
            transaction.discard();
            e.printStackTrace();
        } finally {
            System.err.println(jedis.get("key_a"));
            System.err.println(jedis.get("key_b"));
            System.err.println(jedis.get("key_c"));
            // 关闭连接
            jedis.close();
        }

    }
}
