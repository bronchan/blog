package com.chan.service.impl;

import com.chan.dao.BlogDao;
import com.chan.dto.*;
import com.chan.exception.NotFountException;
import com.chan.pojo.Blog;
import com.chan.pojo.BlogsAndTags;
import com.chan.service.BlogService;
import com.chan.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private BlogsAndTagsServiceImpl blogsAndTagsService;
    @Autowired
    private BlogsAndTags blogsAndTags;

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param []
    *@Return java.util.List<com.chan.dto.BlogInfo>
    */
    @Override
    public List<BlogInfo> getAllFirstPageBlog() {
        return blogDao.getAllFirstPageBlog();
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param []
    *@Return java.util.List<com.chan.dto.RecommendBlog>
    */
    @Override
    public List<RecommendBlog> getRecommendBlog() {
        return blogDao.getRecommendBlog();
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param [keyword]
    *@Return java.util.List<com.chan.dto.BlogInfo>
    */
    @Override
    public List<BlogInfo> getAllSearch(String keyword) {
        return blogDao.getAllSearch(keyword);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param [id]
    *@Return com.chan.dto.DetailedBlog
    */
    @Override
    public DetailedBlog getDetailedBlogById(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlogById(id);
        if (detailedBlog == null) {
            throw new NotFountException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param [typeId]
    *@Return java.util.List<com.chan.dto.BlogInfo>
    */
    @Override
    public List<BlogInfo> getTypeOfBlog(Long typeId) {
        return blogDao.getTypeOfBlog(typeId);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param [tagId]
    *@Return java.util.List<com.chan.dto.BlogInfo>
    */
    @Override
    public List<BlogInfo> getTagOfBlog(Long tagId) {
        return blogDao.getTagOfBlog(tagId);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param []
    *@Return java.util.List<com.chan.dto.EditBlogInfo>
    */
    @Override
    public List<EditBlogInfo> getEditBlogInfo() {
        return blogDao.getEditBlogInfo();
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 16:59
    *@Param [searchInfo]
    *@Return java.util.List<com.chan.dto.EditBlogInfo>
    */
    @Override
    public List<EditBlogInfo> getEditBlogInfoBySearchInfo(SearchInfo searchInfo) {
        return blogDao.getEditBlogInfoBySearchInfo(searchInfo);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 17:00
    *@Param []
    *@Return java.lang.Long
    */
    @Override
    public Long getMaxBlogId() {
        return blogDao.getMaxBlogId();
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 17:00
    *@Param [blog]
    *@Return int
    */
    @Override
    public int saveBlog(Blog blog) {
        //        获取博客的最大id
        Long blogId = blogDao.getMaxBlogId() + 1L;
        blog.setViews(0);
        blog.setId(blogId);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());

        //        保存与blog关联的tagId
        String[] split = blog.getTagIds().split(",");
        for (String s : split) {
            blogsAndTags.setTagId(Long.parseLong(s));
            blogsAndTags.setBlogId(blogId);
            blogsAndTagsService.addBlogsAndTags(blogsAndTags);
        }
        return blogDao.saveBlog(blog);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 17:00
    *@Param [id]
    *@Return int
    */
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Override
    public int addRecommendCount(Long id) {
        return blogDao.addRecommendCount(id);
    }

    @Override
    public int addViews(Long id) {
        return blogDao.addViews(id);
    }

    @Override
    public int blogTotal() {
        return blogDao.blogTotal();
    }

    @Override
    public int views() {
        return blogDao.views();
    }
}
