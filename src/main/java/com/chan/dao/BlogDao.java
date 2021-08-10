package com.chan.dao;

import com.chan.dto.*;
import com.chan.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bronchan
 */
@Repository
@Mapper
public interface BlogDao {
//    获取首页的博客显示列表，id，标题，描述，作者信息等
    List<BlogInfo> getAllFirstPageBlog();

//    获取推荐博客的标题id
    List<RecommendBlog> getRecommendBlog();

//    根据关键字搜索博客
    List<BlogInfo> getAllSearch(String keyword);

//    获取具体的博客
    DetailedBlog getDetailedBlogById(Long id);

//    根据分类获取博客
    List<BlogInfo> getTypeOfBlog(@Param(("typeId")) Long typeId);

    //    根据分类获取博客
    List<BlogInfo> getTagOfBlog(@Param(("tagId")) Long tagId);

//    获取博客编辑页的博客信息
    List<EditBlogInfo> getEditBlogInfo();

//    管理后台根据搜索信息搜索出博客信息
    List<EditBlogInfo> getEditBlogInfoBySearchInfo(SearchInfo searchInfo);

//    保存博客
    int saveBlog(Blog blog);

//    获取最大博客id
    Long getMaxBlogId();

//    删除博客
    int deleteBlog(Long id);

//    根据id获取博客
    Blog getBlogById(Long id);

//    修改博客
    int updateBlog(Blog blog);

//    点击推荐,增加推荐数
    int addRecommendCount(Long id);

//    增加阅读数
    int addViews(Long id);

//    文章总数
    int blogTotal();

//    访问总数
    int views();
}
