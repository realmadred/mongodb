package com.feng.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther Administrator
 * @date 2017/9/1
 * @description 描述
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class NoticeDaoTest {

    @Autowired
    private NoticeDao noticeDao;

    @Test
    public void save() throws Exception {
        noticeDao.save(102,"feng",new Notice());
    }

    @Test
    public void update() throws Exception {
        Notice notice = new Notice();
        notice.setContent("ddd");
        notice.setCreator("ccc");
        noticeDao.update(102,"abc",notice);
    }

    @Test
    public void get() throws Exception {
        noticeDao.get(102,"abc");
    }

    @Test
    public void remove() throws Exception {
        noticeDao.remove(102,"abc");
    }

}