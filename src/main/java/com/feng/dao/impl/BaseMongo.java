package com.feng.dao.impl;

import com.feng.dao.BaseNosql;
import com.feng.dao.util.MongoDBUtils;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @auther lf
 * @date 2017/9/5
 * @description mongodb 基础操作
 */
@Repository
public class BaseMongo implements BaseNosql {

    protected MongoCollection<Document> collection;

    protected void init(String collectionStr){
        this.collection = MongoDBUtils.getCollection(collectionStr);
    }

    /**
     * 插入数据
     *
     * @param json 值
     */
    @Override
    public void insert(final String json) {
        MongoDBUtils.add(collection,json);
    }

    /**
     * 插入数据
     *
     * @param map 值
     */
    @Override
    public void insert(final Map<String, Object> map) {
        MongoDBUtils.add(collection,map);
    }

    /**
     * 根据key查询
     *
     * @param id id
     * @return
     */
    @Override
    public Map<String,Object> findById(final String id) {
        return MongoDBUtils.findById(collection,id);
    }

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @Override
    public int deleteById(final String id) {
        return MongoDBUtils.deleteById(collection,id);
    }

    /**
     * 更新数据
     *
     * @param id   id
     * @param data
     * @return
     */
    @Override
    public int updateById(final String id, final Map<String, Object> data) {
        return MongoDBUtils.updateById(collection,id,new Document(data));
    }
}
