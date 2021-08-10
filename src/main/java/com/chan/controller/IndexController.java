package com.chan.controller;

import com.chan.dto.*;
import com.chan.pojo.User;
import com.chan.service.impl.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author bronchan
 * 博客首页
 */
@Controller
public class IndexController {

    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TypeServiceImpl typeService;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    TotalsDto totalsDto;


    @GetMapping("/")
    public String toIndex(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
//        设置分页参数
        PageHelper.startPage(pageNum,6);
//        查询首页预览的博客列表
        List<BlogInfo> allBlogInfo = blogService.getAllFirstPageBlog();
//        查询推荐博客的信息
        List<RecommendBlog> recommendBlog = blogService.getRecommendBlog();


        PageInfo<BlogInfo> pageInfo = new PageInfo<>(allBlogInfo);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("recommendedBlogs",recommendBlog);

        return "index1";
//        return "index";
    }


    @GetMapping("/search")
    public String getSearch(Model model,
                            @RequestParam String keyword,
                            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum
                            ){

        PageHelper.startPage(pageNum,5);

        List<BlogInfo> allSearch = blogService.getAllSearch(keyword);
        User user = userService.getUser();

        for (BlogInfo blogInfo : allSearch){
            blogInfo.setNickname(user.getNickname());
            blogInfo.setAvatar(user.getAvatar());
        }

        PageInfo<BlogInfo> pageInfo = new PageInfo<>(allSearch);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",keyword);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "search1";
//        return "search";
    }

    @GetMapping("/blog/{id}")
    public String getBlogById(@PathVariable(value = "id") Long id,Model model){
//        进入博客增加阅读数
        blogService.addViews(id);
//        获取详细博客
        DetailedBlog detailedBlogById = blogService.getDetailedBlogById(id);
//        获取父评论并在父评论下获取子评论
        List<ParentComments> parentComments = commentService.getParentComments(id);
        for (ParentComments comments : parentComments) {
            comments.setSunComments(commentService.getCommentsByDidId(comments.getId(),comments.getBlogId()));
        }
        model.addAttribute("comments", parentComments);
        model.addAttribute("blog", detailedBlogById);
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "blog";
        return "blog1";
    }

    /**
    *@Author chenqitao
    *@Description 推荐博客操作
    *@Date 2021/8/9 15:52
    *@Param [id, model, session]
    *@Return java.lang.String
    */
    @PostMapping("/blog/userRecommend")
    @ResponseBody
    public String doRecommend(Long id , HttpSession session){
        if (session.isNew()) {
            blogService.addRecommendCount(id);
            return "ok";
        }else {
            return "您已经推荐过了";
        }
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }

}
