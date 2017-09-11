package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/5.
 * dsdb搜索时间
 */
public class MongoTime extends MongoPage implements Serializable {

    private static final long serialVersionUID = -8547012765926577010L;

    private long startTime;// 开始时间
    private long endTime;// 结束时间

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
