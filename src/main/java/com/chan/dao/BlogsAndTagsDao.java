package com.chan.dao;

import com.chan.pojo.BlogsAndTags;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author bronchan
 * @ClassName BlogsAndTagsDao
 * @date 2021/8/5 15:08
 * @Version 1.0
 * @Description TODO
 */
@Mapper
@Repository
public interface BlogsAndTagsDao {
    int addBlogsAndTags(BlogsAndTags blogsAndTags);
    int deleteBlogsAndTagsByBlogId(Long blogId);
}
