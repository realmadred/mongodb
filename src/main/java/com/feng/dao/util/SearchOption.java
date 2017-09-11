package com.feng.dao.util;

import com.feng.dao.util.MongoConst;

import java.io.Serializable;

/**
 * @auther Administrator
 * @date 2017/9/5
 * @description 查询条件
 */
public class SearchOption implements Serializable {

    private static final long serialVersionUID = 7338488039582381017L;
    private int start = 0; // 开始
    private int page = 1; // 第几页
    private int pageSize; // 一页大小
    private String order = MongoConst.ID; // 排序字段
    private int orderAsc = -1; // 是否升序1 是
    private static final int MAX_PAGE_SIZE = 50;

    private SearchOption() {
    }

    private SearchOption(final int page, final int pageSize, final String order, final int orderAsc) {
        this.page = page <= 0 ? 1 : page;
        setPageSize(pageSize);
        this.start = (page - 1) * pageSize;
        this.order = order;
        this.orderAsc = orderAsc;
    }

    public static SearchOption newOption() {
        return new SearchOption();
    }

    public static SearchOption newOption(final int page, final int pageSize) {
        return new SearchOption().setPage(page).setPageSize(pageSize);
    }

    public static SearchOption newOption(final int page, final int pageSize, String order, int orderAsc) {
        return new SearchOption(page, pageSize, order, orderAsc);
    }

    public int getStart() {
        return start;
    }

    public SearchOption setStart(final int start) {
        this.start = start;
        return this;
    }

    private void setStart() {
        this.start = (page - 1) * pageSize;
    }

    public int getPage() {
        return page;
    }

    public SearchOption setPage(final int page) {
        this.page = page <= 0 ? 1 : page;
        setStart();
        return this;
    }

    public String getOrder() {
        return order;
    }

    public SearchOption setOrder(final String order, final int orderAsc) {
        this.order = order;
        this.orderAsc = orderAsc;
        return this;
    }

    public int getOrderAsc() {
        return orderAsc;
    }

    public int getPageSize() {
        return pageSize;
    }

    public SearchOption setPageSize(final int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : (pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize);
        setStart();
        return this;
    }

    @Override
    public String toString() {
        return "SearchOption{" +
                "start=" + start +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", order='" + order + '\'' +
                ", orderAsc=" + orderAsc +
                '}';
    }
}
