package com.feng.dao;


import com.feng.search.MongoTime;

/**
 * @auther lf
 * @date 2017/9/8
 * @description 预警日志
 */
public interface WarningLogNosql extends SearchBaseNosql<MongoTime> {

    /**
     * 更新指定时间范围的阅读状态
     * @param start 开始
     * @param end 结束
     */
    void updateReadByTimeRange(long start, long end);
}
