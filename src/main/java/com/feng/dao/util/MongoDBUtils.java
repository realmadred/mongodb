package com.feng.dao.util;

import com.google.common.collect.Lists;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther lf
 * @date 2017/9/4
 * mongodb工具类
 */
public class MongoDBUtils {

    // 客户端连接池
    private static final MongoClient MONGO_CLIENT;

    private static final MongoDatabase DATABASE;

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
        // 地址
        ServerAddress address = new ServerAddress(MongoConst.HOST, MongoConst.PORT);
        // 获取客户端
        MONGO_CLIENT = new MongoClient(address, Lists.newArrayList(credential), options);

        // 获取数据库
        DATABASE = getDatabase(MongoConst.DATABASE);

    }

    /**
     * 获取数据库
     * lf
     * 2017-09-05 09:58:47
     *
     * @param database 数据库名
     * @return
     */
    static MongoDatabase getDatabase(@Nonnull String database) {
        return MONGO_CLIENT.getDatabase(database);
    }

    /**
     * 获取集合
     * lf
     * 2017-09-05 10:09:37
     *
     * @param collection 集合
     * @return
     */
    static MongoCollection<Document> getCollection(@Nonnull String collection) {
        return DATABASE.getCollection(collection);
    }

    /**
     * 添加数据
     *
     * @param collectionStr 集合
     * @param json          json
     */
    public static void add(@Nonnull String collectionStr, String json) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || StringUtils.isBlank(json)) return;
        collection.insertOne(Document.parse(json));
    }

    /**
     * 添加数据
     *
     * @param collectionStr 集合
     * @param map           数据
     */
    public static void add(@Nonnull String collectionStr, Map<String, Object> map) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || MapUtils.isEmpty(map)) return;
        collection.insertOne(new Document(map));
    }

    /**
     * 添加数据
     *
     * @param collectionStr 集合
     * @param list          数据
     */
    public static void addMany(@Nonnull String collectionStr, List<Map<String, Object>> list) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || CollectionUtils.isEmpty(list)) return;
        final int size = list.size();
        List<Document> documents = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            documents.add(new Document(list.get(i)));
        }
        collection.insertMany(documents);
    }

    /**
     * 根据id查询
     * lf
     * 2017-09-05 11:07:03
     *
     * @param collectionStr 集合
     * @param id            id
     */
    public static Document findById(@Nonnull String collectionStr, String id) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || StringUtils.isBlank(id)) return null;
        return collection.find(Filters.eq(MongoConst.ID, new ObjectId(id))).first();
    }

    /**
     * 获取数量
     *
     * @param collectionStr 集合
     * @param bson          条件
     * @return
     */
    public long getCount(@Nonnull String collectionStr, Bson bson) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null) return 0;
        return bson == null ? collection.count() : collection.count(bson);
    }

    /**
     * 获取数量
     *
     * @param collectionStr 集合
     * @return
     */
    public long getCount(@Nonnull String collectionStr) {
        return getCount(collectionStr, null);
    }

    /**
     * 查询集合数据
     *
     * @param collectionStr 集合
     * @param filters       过滤条件
     * @param option        分页条件
     * @return
     */
    public static FindIterable<Document> find(@Nonnull String collectionStr, Bson filters, @Nonnull SearchOption option) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        return collection.find(filters).sort(new BasicDBObject(option.getOrder(), option.getOrderAsc()))
                .skip(option.getStart()).batchSize(option.getPageSize());
    }

    /**
     * 根据id删除数据
     * lf
     * 2017-09-05 11:47:54
     *
     * @param collectionStr 集合
     * @param id            id
     * @return
     */
    public int deleteById(@Nonnull String collectionStr, String id) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || StringUtils.isBlank(id)) return 0;
        DeleteResult deleteResult = collection.deleteOne(Filters.eq(MongoConst.ID, new ObjectId(id)));
        return deleteResult == null ? 0 : (int) deleteResult.getDeletedCount();
    }

    /**
     * 根据id更新数据
     *
     * @param collectionStr 集合
     * @param id            id
     * @param newdoc        更新的文档
     * @return
     */
    public int updateById(@Nonnull String collectionStr, String id, Document newdoc) {
        MongoCollection<Document> collection = getCollection(collectionStr);
        if (collection == null || StringUtils.isBlank(id)) return 0;
        UpdateResult updateResult = collection.updateOne(Filters.eq(MongoConst.ID, new ObjectId(id)), new Document("$set", newdoc));
        return updateResult == null ? 0 : (int) updateResult.getModifiedCount();
    }
}
