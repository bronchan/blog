package com.chan.controller.admin;

import com.chan.dto.TotalsDto;
import com.chan.pojo.User;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import com.chan.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author bronchan
 */

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    TotalsDto totalsDto;

    @Autowired
    BlogServiceImpl blogService;

    @GetMapping
    public String toLogin(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String getAdminLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes attributes,
                                Model model){

        User user = userService.getUserByInfo(username, password);

        if (user != null) {
//            别把密码暴露在服务器上
            user.setPassword(null);
            session.setAttribute("user",user);
            TotalsDto totals = getTotals();
            model.addAttribute("totals",totals);
            return "admin/index";
        }
//        model保存的属性传递不到重定向的页面
        attributes.addFlashAttribute("message","用户名或密码错误");
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}

