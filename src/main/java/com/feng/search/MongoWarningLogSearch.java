package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/5.
 * 预警信息搜索vo
 */
public class MongoWarningLogSearch extends MongoTime implements Serializable {

    private static final long serialVersionUID = -5823409060183136681L;

    private String warnItemCode; // 警告代码

    private int isRead = -1;// 是否已读 0 未读 1 已读 -1所有

    public String getWarnItemCode() {
        return warnItemCode;
    }

    public void setWarnItemCode(String warnItemCode) {
        this.warnItemCode = warnItemCode;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
