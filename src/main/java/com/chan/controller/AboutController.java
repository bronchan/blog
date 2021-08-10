package com.chan.controller;

import com.chan.dto.TotalsDto;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author bronchan
 */
@Controller
public class AboutController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    TotalsDto totalsDto;

    @GetMapping("/about")
    public String getAboutMe(Model model){
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "about1";
//        return "about";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}
