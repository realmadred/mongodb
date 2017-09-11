package com.feng.dao;

import com.feng.dao.BaseNosql;

import java.util.List;
import java.util.Map;

/**
 * @auther lf
 * @date 2017/9/8
 * @description nosql 基础搜索接口
 */
public interface SearchBaseNosql<T> extends BaseNosql {

    List<Map<String,Object>> searchLogs(T search);

    long getCount(T search);
}
