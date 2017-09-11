package com.feng.dao.impl;

import com.feng.dao.SearchBaseNosql;
import com.feng.dao.util.MongoDBUtils;
import com.feng.dao.util.SearchOption;
import com.feng.search.MongoTime;
import com.google.common.collect.Lists;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auther lf
 * @date 2017/9/5
 * @description mongodb 基础操作
 */
@Repository
public abstract class SearchBaseMongo extends BaseMongo implements SearchBaseNosql<MongoTime> {

    @Override
    public List<Map<String, Object>> searchLogs(final MongoTime search) {
        if (search == null) return null;
        final int page = search.getPage();
        final int rows = search.getRows();
        final String orderField = search.getOrderField();
        final int asc = search.getAsc();
        SearchOption option = SearchOption.newOption(page, rows);
        if (StringUtils.isNotBlank(orderField)) option.setOrder(orderField,asc);
        FindIterable<Document> documents = MongoDBUtils.find(collection, Filters.and(getBsons(search)), option);
        final List<Map<String, Object>> result = Lists.newArrayListWithCapacity(rows);
        if (documents != null) {
            documents.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    result.add(document);
                }
            });
        }
        return result;
    }

    @Override
    public long getCount(final MongoTime search) {
        if (search == null) return 0;
        return MongoDBUtils.getCount(collection, Filters.and(getBsons(search)));
    }

    /**
     * 查询条件
     *
     * @param search 搜索条件
     * @return
     */
    protected abstract List<Bson> getBsons(final MongoTime search);

}
