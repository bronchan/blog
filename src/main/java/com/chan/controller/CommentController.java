package com.chan.controller;

import com.chan.dto.DetailedBlog;
import com.chan.dto.ParentComments;
import com.chan.dto.TotalsDto;
import com.chan.pojo.Comment;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author bronchan
 */

@Controller
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    TotalsDto totalsDto;



    @PostMapping("/comments")
    public String getComments(Comment comment){
        commentService.addComment(comment);
        System.out.println("comment = " + comment);
        return "redirect:/comments/"+comment.getBlogId();
    }

    @GetMapping("/comments/{id}")
    public String getBlogPage(@PathVariable("id") Long blogId, Model model){
        List<ParentComments> parentComments = commentService.getParentComments(blogId);
        for (ParentComments comments : parentComments) {
            comments.setSunComments(commentService.getCommentsByDidId(comments.getId(),comments.getBlogId()));
        }
        //        获取详细博客
        DetailedBlog detailedBlogById = blogService.getDetailedBlogById(blogId);

        model.addAttribute("comments", parentComments);
        model.addAttribute("blog", detailedBlogById);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);

        return "blog1";
//        return "blog";
    }

    @GetMapping("/messageBoard")
    public String getMessageBoard(Model model){
        List<ParentComments> parentMessages = commentService.getParentMessages();
        for (ParentComments comment : parentMessages) {
            comment.setSunComments(commentService.getMessagesByDidId(comment.getId()));
        }

        model.addAttribute("comments",parentMessages);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "message";
    }

    @PostMapping("/addMessage")
    public String addMessages(Comment comment){

        commentService.addComment(comment);
//        System.out.println("comment = " + comment);

        return "redirect:/messageBoard";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}
