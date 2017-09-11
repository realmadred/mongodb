package com.feng.dao.impl;

import com.feng.dao.PlatformLogNosql;
import com.feng.dao.util.MongoConst;
import com.feng.search.MongoPlatformLogSearch;
import com.feng.search.MongoTime;
import com.google.common.collect.Lists;
import com.mongodb.client.model.Filters;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @auther lf
 * @date 2017/9/5
 * @description 平台操作日志
 */
@Repository
public class PlatformLogMongo extends SearchBaseMongo implements PlatformLogNosql {

    private static final String PLATFORM_LOG_COLLECTION = "platform_log";

    public PlatformLogMongo() {
        super();
        super.init(PLATFORM_LOG_COLLECTION);
    }

    /**
     * 查询条件
     * @param search 搜索条件
     * @return
     */
    @Override
    public List<Bson> getBsons(final MongoTime search) {
        MongoPlatformLogSearch logSearch = (MongoPlatformLogSearch)search;
        Integer memberId = logSearch.getMemberId();
        String name = logSearch.getName();
        String method = logSearch.getMethod();
        String methodDescribe = logSearch.getMethodDescribe();
        long startTime = search.getStartTime();
        long endTime = search.getEndTime();
        List<Bson> list = Lists.newArrayList();
        list.add(Filters.lte("createTime", endTime == 0 ? System.currentTimeMillis() : endTime));
        list.add(Filters.gte("createTime", startTime));
        if (memberId != null) {
            list.add(Filters.eq("userId", memberId));
        }
        if (StringUtils.isNotBlank(name)) {
            final Pattern compile = Pattern.compile(MongoConst.LIKE_START + name + MongoConst.LIKE_END);
            list.add(Filters.regex("name", compile));
        }
        if (StringUtils.isNotBlank(method)) {
            final Pattern compile = Pattern.compile(MongoConst.LIKE_START + method + MongoConst.LIKE_END);
            list.add(Filters.regex("methodName", compile));
        }
        if (StringUtils.isNotBlank(methodDescribe)) {
            final Pattern compile = Pattern.compile(MongoConst.LIKE_START + methodDescribe + MongoConst.LIKE_END);
            list.add(Filters.regex("methodDescribe", compile));
        }
        return list;
    }
}
