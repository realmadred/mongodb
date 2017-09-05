package com.feng.dao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @auther lf
 * @date 2017/9/4
 * @description mongodb 常量
 */
public class MongoConst {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConst.class);
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(MongoDBUtils.class.getClassLoader().getResourceAsStream("mongo.properties"));
        } catch (IOException e) {
            LOGGER.error("加载配置文件失败！");
        }
    }

    /**
     * 获取整数
     * @param prop Properties
     * @param key key
     * @param defaultValue 默认值
     * @return
     */
    public static Integer getPropertiesInt(Properties prop, String key, Integer defaultValue){
        if (prop == null) return defaultValue;
        return Integer.valueOf(prop.getProperty(key, Integer.toString(defaultValue)));
    }

    /**
     * 获取int值
     * @param key key
     * @param defaultValue 默认值
     * @return
     */
    private static Integer getInt(String key, Integer defaultValue){
        return getPropertiesInt(PROPERTIES,key,defaultValue);
    }

    // 主机地址
    static final String HOST = PROPERTIES.getProperty("mongo.host","192.168.127.136");

    // 端口
    static final int PORT = getInt("mongo.port",27017);

    // 数据库
    static final String DATABASE = PROPERTIES.getProperty("mongo.database","test");

    // 用户名
    static final String USERNAME = PROPERTIES.getProperty("mongo.username","test");

    // 密码
    static final String PASSWORD = PROPERTIES.getProperty("mongo.password","12345678");

    // 最大连接数
    static final int CONNECTIONS_PER_HOST = getInt("mongo.max",50);

    // 最小连接数
    static final int MIN_CONNECTIONS_PER_HOST = getInt("mongo.min",10);

    // 单个连接的线程队列
    static final int CONNECTIONS_THREADS = getInt("mongo.threads",50);

    // 连接超时时间
    static final int CONNECT_TIMEOUT = getInt("mongo.connectTimeout",15000);

    // 最大等待时间
    static final int MAX_WAIT_TIME = getInt("mongo.maxWaitTime",120000);


}
