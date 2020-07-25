//package com.bs.pro.service.impl;
//
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@Log4j2
//public class RedisServiceImpl {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public void del(String key) {
//        redisTemplate.delete(key);
//    }
//
//    public String get(String key) {
//        return (String) redisTemplate.opsForValue().get(key);
//    }
//
//    public Object getObj(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public void set(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    public void set(String key, String value, int seconds) {
//        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
//    }
//
//    public void setObj(String key, Object value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    public void setObj(String key, Object value, int seconds) {
//        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
//    }
//
//    public void setex(String key, String value, int seconds) {
//        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
//    }
//
//
//    // 获取从list尾部弹出最后一个值
//    public String rpop(String key) {
//        return (String) redisTemplate.opsForList().rightPop(key);
//    }
//
//    // 写入list头部
//    public void lpush(String key, String value) {
//        redisTemplate.opsForList().leftPush(key, value);
//    }
//
//    public Long llen(String key) {
//        return redisTemplate.opsForList().size(key);
//    }
//
//    public void hset(String key, String field, String value) {
//        redisTemplate.opsForHash().put(key, field, value);
//    }
//
//    public String hget(String key, String field) {
//        return (String) redisTemplate.opsForHash().get(key, field);
//    }
//
//    //返回所有的filed
//    public Set<String> hkeys(String key) {
//        return redisTemplate.opsForHash().keys(key);
//    }
//
//    /**
//     * 订阅需要自己写Listener实现
//     * {@Link org.springframework.data.redis.connection.MessageListener}
//     *
//     * @param channel
//     * @param message
//     */
////    public void subscribe(JedisPubSub listener, String key) {
////        redisTemplate.convertAndSend();
////    }
//    public void publish(String channel, String message) {
//        redisTemplate.convertAndSend(channel, message);
//    }
//}
