package com.feng.dao.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther lf
 * @date 2017/9/4
 * mongodb工具类
 */
public class MongoDBUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBUtils.class);
    // 客户端连接池
    private static final MongoClient MONGO_CLIENT;
    public static final String ID = "_id";

    static {
        final MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(MongoConst.CONNECTIONS_PER_HOST)   // 最大连接
                .minConnectionsPerHost(MongoConst.MIN_CONNECTIONS_PER_HOST) // 最小连接
                .threadsAllowedToBlockForConnectionMultiplier(MongoConst.CONNECTIONS_THREADS)
                .maxWaitTime(MongoConst.MAX_WAIT_TIME)
                .connectTimeout(MongoConst.CONNECT_TIMEOUT)
                .build();

        // 验证
        final MongoCredential credential = MongoCredential
                .createCredential(MongoConst.USERNAME, MongoConst.DATABASE, MongoConst.PASSWORD.toCharArray());
        List<MongoCredential> list = new ArrayList<>();
        list.add(credential);
        ServerAddress address = new ServerAddress(MongoConst.HOST, MongoConst.PORT);
        MONGO_CLIENT = new MongoClient(address, list, options);
    }

    public static void main(String[] args) {
        System.out.println(MONGO_CLIENT);
    }

}
