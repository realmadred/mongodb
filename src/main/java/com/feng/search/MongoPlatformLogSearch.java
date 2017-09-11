package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/5.
 * 平台搜索vo
 */
public class MongoPlatformLogSearch extends MongoMemberLogSearch implements Serializable {

    private static final long serialVersionUID = -8938847661573039580L;

    private String methodDescribe; // 方法描述

    public String getMethodDescribe() {
        return methodDescribe;
    }

    public void setMethodDescribe(String methodDescribe) {
        this.methodDescribe = methodDescribe;
    }
}
