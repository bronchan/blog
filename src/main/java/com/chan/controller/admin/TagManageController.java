package com.chan.controller.admin;

import com.chan.dto.TotalsDto;
import com.chan.pojo.Tag;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import com.chan.service.impl.TagServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bronchan
 * @ClassName TagManageController
 * @date 2021/8/5 21:14
 * @Version 1.0
 * @Description TODO
 */
@Controller
@RequestMapping("/admin")
public class TagManageController {
    @Autowired
    TagServiceImpl tagService;

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    TotalsDto totalsDto;

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 23:42
    *@Param [model, pageNum]
    *@Return java.lang.String
    */
    @GetMapping("/tags")
    public String toTagsPage(Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Tag> allTags = tagService.getAllTags();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(allTags);
        model.addAttribute("pageInfo",tagPageInfo);
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/tags";
        return "admin/tags1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 23:51
    *@Param []
    *@Return java.lang.String
    */
    @GetMapping("toAddTag")
    public String toAddTagPage(Model model){
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/addTag";
        return "admin/addTag1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 23:58
    *@Param [tag, model]
    *@Return java.lang.String
    */
    @PostMapping("/addTag")
    public String addTag(Tag tag,Model model){
        int i = tagService.addTag(tag);
        if (i > 0){
            return "redirect:/admin/tags";
        }

        model.addAttribute("message","增加标签错误");
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/addTag";
        return "admin/addTag1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:06
    *@Param [id, model]
    *@Return java.lang.String
    */
    @GetMapping("/toUpdateTag/{id}")
    public String toUpdateTag(@PathVariable(value = "id")Long id, Model model){
        Tag tagById = tagService.getTagById(id);
        model.addAttribute("tag",tagById);
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "admin/updateTag";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:15
    *@Param [tag, model]
    *@Return java.lang.String
    */
    @PostMapping("/updateTag")
    public String updateTag(Tag tag,Model model){
        int i = tagService.updateTag(tag);
        if (i > 0){
            return "redirect:/admin/tags";
        }
        Tag tagById = tagService.getTagById(tag.getId());
        model.addAttribute("tag",tagById);
        model.addAttribute("message","新增标签失败");
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "admin/updateTag";
    }

    @GetMapping("/deleteTag/{id}")
    public String deleteTag(Model model,
                            @PathVariable(value = "id") Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }

}
