package com.feng.dao;

import java.util.Map;

/**
 * @auther lf
 * @date 2017/9/5
 * @description nosql 基础操作接口
 */
public interface BaseNosql {

    /**
     * 插入数据
     *
     * @param json 值
     */
    void insert(String json);

    /**
     * 插入数据
     *
     * @param map 值
     */
    void insert(Map<String, Object> map);

    /**
     * 根据key查询
     *
     * @param id id
     * @return
     */
    Map<String,Object> findById(String id);

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    int deleteById(String id);

    /**
     * 更新数据
     * @param id id
     * @return
     */
    int updateById(String id, Map<String, Object> data);

}
