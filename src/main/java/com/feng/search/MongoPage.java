package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/5.
 * mongodb分页
 */
public class MongoPage extends MongoSort implements Serializable {

    private static final long serialVersionUID = -7881015222139625996L;

    private int page = 0 ; // 第几页
    private int rows = 10 ; // 记录数

    public MongoPage() {
    }

    public MongoPage(int page, int rows) {
        this.page = page < 0 ? 0 : page;
        this.rows = rows <= 0 ? 10 : rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows <= 0 ? 10 : rows;
    }
}
