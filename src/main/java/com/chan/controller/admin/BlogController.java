package com.chan.controller.admin;

import com.chan.dto.EditBlogInfo;
import com.chan.dto.SearchInfo;
import com.chan.dto.TotalsDto;
import com.chan.pojo.Blog;
import com.chan.pojo.Tag;
import com.chan.pojo.Type;
import com.chan.pojo.User;
import com.chan.service.impl.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author bronchan
 */

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TypeServiceImpl typeService;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    private BlogsAndTagsServiceImpl blogsAndTagsService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    TotalsDto totalsDto;



    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 14:45
    *@Param [model]
    *@Return void
    */
    public void getAllTypes(Model model){
        List<Type> allTypes = typeService.getAllTypes();
        model.addAttribute("types",allTypes);
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 15:08
    *@Param [model]
    *@Return void
    */
    public void getAllTags(Model model){
        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("tags",allTags);
    }


    /**
    *@Author chenqitao
    *@Description 查出后台博客编辑blogs页面的信息，blog的标题，更新时间，是否推荐，还有类型
    *@Date 2021/8/1 14:42
    *@Param [model, pageNum]
    *@Return java.lang.String
    */
    @GetMapping("/blogs")
    public String toBlogList(Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<EditBlogInfo> editBlogInfo = blogService.getEditBlogInfo();
        PageInfo<EditBlogInfo> editBlogInfoPageInfo = new PageInfo<>(editBlogInfo);
        model.addAttribute("pageInfo",editBlogInfoPageInfo);
        getAllTypes(model);
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);

//        return "admin/blogs";
        return "admin/blogs1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 16:30
    *@Param [searchInfo, model, pageNum]
    *@Return java.lang.String
    */
    @PostMapping("/blogs/search")
    public String getBlogsBySearch(SearchInfo searchInfo,Model model,
                                   @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<EditBlogInfo> editBlogInfoBySearchInfo = blogService.getEditBlogInfoBySearchInfo(searchInfo);
        getAllTypes(model);
        PageInfo<EditBlogInfo> pageInfo = new PageInfo<>(editBlogInfoBySearchInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功");
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/blogs";
        return "admin/blogs1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 22:05
    *@Param [model]
    *@Return java.lang.String
    */
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){
        getAllTypes(model);
        getAllTags(model);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/addBlog";
        return "admin/addBlog1";
    }


    /**
    *@Author chenqitao
    *@Description 新增博客
    *@Date 2021/8/5 13:27
    *@Param [blog]
    *@Return java.lang.String
    */
    @PostMapping("/addBlogs")
    public String addBlogs(Blog blog, HttpSession session, RedirectAttributes attributes) {
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        int i = blogService.saveBlog(blog);
        if (i>0){
            attributes.addFlashAttribute("message","发布博客成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
    *@Author chenqitao
    *@Description 删除博客
    *@Date 2021/8/5 16:57
    *@Param [id, attributes]
    *@Return java.lang.String
    */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int i = blogService.deleteBlog(id);
        blogsAndTagsService.deleteBlogsAndTagsByBlogId(id);
        if (i > 0){
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
    *@Author chenqitao
    *@Description 转到博客修改页，并查询修改信息
    *@Date 2021/8/5 18:28
    *@Param [id, model]
    *@Return java.lang.String
    */
    @GetMapping("/blogs/{id}/input")
    public String toUpdate(@PathVariable Long id,Model model) {
        Blog blogById = blogService.getBlogById(id);
        List<Type> allTypes = typeService.getAllTypes();
        List<Tag> allTags = tagService.getAllTags();

        model.addAttribute("blog", blogById);
        model.addAttribute("types", allTypes);
        model.addAttribute("tags", allTags);

        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/updateBlog";
        return "admin/updateBlog1";
    }

    /**
    *@Author chenqitao
    *@Description 修改博客内容
    *@Date 2021/8/5 21:12
    *@Param [blog, model]
    *@Return java.lang.String
    */
    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog,Model model){
        blogService.updateBlog(blog);
        model.addAttribute("message","修改博客成功");
        return "redirect:/admin/blogs";
    }


    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}
