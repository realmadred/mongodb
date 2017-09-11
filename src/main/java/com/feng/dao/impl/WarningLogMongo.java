package com.feng.dao.impl;

import com.feng.dao.WarningLogNosql;
import com.feng.dao.util.MongoConst;
import com.feng.dao.util.MongoDBUtils;
import com.feng.search.MongoTime;
import com.feng.search.MongoWarningLogSearch;
import com.google.common.collect.Lists;
import com.mongodb.client.model.Filters;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
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
public class WarningLogMongo extends SearchBaseMongo implements WarningLogNosql {

    private static final String PLATFORM_LOG_COLLECTION = "warning_log";

    public WarningLogMongo() {
        super();
        super.init(PLATFORM_LOG_COLLECTION);
    }

    /**
     * 更新指定时间范围的阅读状态
     * lf
     * 2017-09-08 14:40:32
     * @param start 开始
     * @param end   结束
     */
    @Override
    public void updateReadByTimeRange(final long start, final long end) {
        MongoDBUtils.update(collection, Filters.and(Filters.gte(MongoConst.CREATE_TIME,start),
                Filters.lte(MongoConst.CREATE_TIME,end)),new Document("isRead",1));
    }

    /**
     * 查询条件
     * @param search 搜索条件
     * @return
     */
    @Override
    public List<Bson> getBsons(final MongoTime search) {
        MongoWarningLogSearch logSearch = (MongoWarningLogSearch)search;
        String warnItemCode = logSearch.getWarnItemCode();
        int isRead = logSearch.getIsRead();
        long startTime = search.getStartTime();
        long endTime = search.getEndTime();
        List<Bson> list = Lists.newArrayList();
        list.add(Filters.lte("createTime", endTime == 0 ? System.currentTimeMillis() : endTime));
        list.add(Filters.gte("createTime", startTime));
        if (isRead != -1) {
            list.add(Filters.eq("isRead", isRead));
        }
        if (StringUtils.isNotBlank(warnItemCode)) {
            final Pattern compile = Pattern.compile(MongoConst.LIKE_START + warnItemCode + MongoConst.LIKE_END);
            list.add(Filters.regex("warnItemCode", compile));
        }
        return list;
    }
}
