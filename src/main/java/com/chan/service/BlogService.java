package com.chan.service;

import com.chan.dto.*;
import com.chan.pojo.Blog;

import java.util.List;

public interface BlogService {
    List<BlogInfo> getAllFirstPageBlog();
    List<RecommendBlog> getRecommendBlog();
    List<BlogInfo> getAllSearch(String keyword);
    DetailedBlog getDetailedBlogById(Long id);
    List<BlogInfo> getTypeOfBlog(Long typeId);
    List<BlogInfo> getTagOfBlog(Long tagId);
    List<EditBlogInfo> getEditBlogInfo();
    List<EditBlogInfo> getEditBlogInfoBySearchInfo(SearchInfo searchInfo);
    Long getMaxBlogId();
    int saveBlog(Blog blog);
    int deleteBlog(Long id);
    Blog getBlogById(Long id);
    int updateBlog(Blog blog);
    int addRecommendCount(Long id);
    int addViews(Long id);
    int blogTotal();
    int views();

}
