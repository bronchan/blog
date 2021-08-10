package com.chan.service;

import com.chan.pojo.BlogsAndTags;
import org.springframework.stereotype.Service;

/**
 * @author bronchan
 * @ClassName BlogsAndTagsService
 * @date 2021/8/5 15:11
 * @Version 1.0
 * @Description TODO
 */
public interface BlogsAndTagsService {
    int addBlogsAndTags(BlogsAndTags blogsAndTags);
    int deleteBlogsAndTagsByBlogId(Long blogId);
}
