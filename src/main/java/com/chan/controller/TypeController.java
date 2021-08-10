package com.chan.controller;

import com.chan.dto.BlogInfo;
import com.chan.dto.BlogsOfType;
import com.chan.dto.TotalsDto;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import com.chan.service.impl.TypeServiceImpl;
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
 * 根据分类显示博客
 */
@Controller
public class TypeController {
    @Autowired
    TypeServiceImpl typeService;
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    TotalsDto totalsDto;

    @GetMapping("/types")
    public String getTypePage(Long id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<BlogsOfType> allType = typeService.getAllBlogsOfType();

        if (id==-1){
            id = allType.get(0).getId();
        }

        model.addAttribute("types", allType);
//        根据分类id查找其下的博客
        List<BlogInfo> typeOfBlog = blogService.getTypeOfBlog(id);

        PageInfo<BlogInfo> blogInfoPageInfo = new PageInfo<>(typeOfBlog);
        model.addAttribute("pageInfo", blogInfoPageInfo);
        model.addAttribute("activeTypeId", id);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);

        return "types1";
//        return "types";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }

}
