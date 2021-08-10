package com.chan.service.impl;

import com.chan.dao.BlogsAndTagsDao;
import com.chan.pojo.BlogsAndTags;
import com.chan.service.BlogsAndTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bronchan
 * @ClassName BlogsAndTagsServiceImpl
 * @date 2021/8/5 15:11
 * @Version 1.0
 * @Description TODO
 */
@Service
public class BlogsAndTagsServiceImpl implements BlogsAndTagsService {

    @Autowired
    BlogsAndTagsDao blogsAndTagsDao;

    @Override
    public int addBlogsAndTags(BlogsAndTags blogsAndTags) {
        return blogsAndTagsDao.addBlogsAndTags(blogsAndTags);
    }

    @Override
    public int deleteBlogsAndTagsByBlogId(Long blogId) {
        return blogsAndTagsDao.deleteBlogsAndTagsByBlogId(blogId);
    }
}
