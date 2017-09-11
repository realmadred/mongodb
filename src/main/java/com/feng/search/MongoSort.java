package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/8.
 * mongodb分页
 */
public class MongoSort implements Serializable {

    private static final long serialVersionUID = -6442474621156118022L;
    private String orderField = "" ; // 排序字段
    private int asc = -1 ; // 1 升序 2 降序

    public MongoSort() {
    }

    public MongoSort(final String orderField, final int asc) {
        this.orderField = orderField;
        this.asc = asc;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(final String orderField) {
        this.orderField = orderField;
    }

    public void setOrder(final String orderField, final int asc) {
        this.orderField = orderField;
        this.asc = asc;
    }

    public int getAsc() {
        return asc;
    }

    public void setAsc(final int asc) {
        this.asc = asc;
    }

}
