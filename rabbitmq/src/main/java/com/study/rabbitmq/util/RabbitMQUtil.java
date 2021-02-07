package com.study.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {
    /**
     * 获取MQ的连接
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("127.0.0.1");
        // AMQP：5672
        factory.setPort(5672);
        // vhost
        factory.setVirtualHost("/vhost_admin");
        // 用户名
        factory.setUsername("admin");
        // 密码
        factory.setPassword("admin");
        // 创建连接
        return factory.newConnection();
    }
}
