package com.chan.controller;

import com.chan.dto.BlogInfo;
import com.chan.dto.BlogsOfTag;
import com.chan.dto.TotalsDto;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import com.chan.service.impl.TagServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author bronchan
 */

@Controller
public class TagController {
    @Autowired
    TagServiceImpl tagService;

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    TotalsDto totalsDto;

    @GetMapping("/tags")
    public String getTagPage(Long id, Model model, @RequestParam(defaultValue = "1" ,value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);

        List<BlogsOfTag> allTag = tagService.getAllBlogsOfTag();

        if (id==-1){
            id = allTag.get(0).getId();
        }

        List<BlogInfo> tagOfBlog = blogService.getTagOfBlog(id);

        PageInfo<BlogInfo> blogInfoPageInfo = new PageInfo<>(tagOfBlog);

        model.addAttribute("tags", allTag);
        model.addAttribute("pageInfo", blogInfoPageInfo);
        model.addAttribute("activeTagId", id);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);

//        return "tags";
        return "tags1";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}
