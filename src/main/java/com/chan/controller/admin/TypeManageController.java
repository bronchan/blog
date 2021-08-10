package com.chan.controller.admin;

import com.chan.dto.TotalsDto;
import com.chan.pojo.Type;
import com.chan.service.impl.BlogServiceImpl;
import com.chan.service.impl.CommentServiceImpl;
import com.chan.service.impl.TypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bronchan
 * @ClassName TypeManageController
 * @date 2021/8/5 21:14
 * @Version 1.0
 * @Description TODO
 */
@Controller
@RequestMapping("/admin")
public class TypeManageController {

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    TotalsDto totalsDto;

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 21:45
    *@Param [model, pageNum]
    *@Return java.lang.String
    */
    @GetMapping("/types")
    public String toTypesPage(Model model,
                              @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Type> allTypes = typeService.getAllTypes();
        PageInfo<Type> typePageInfo = new PageInfo<>(allTypes);
        model.addAttribute("pageInfo",typePageInfo);
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/types";
        return "admin/types1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/5 22:29
    *@Param [typId, model]
    *@Return java.lang.String
    */
    @GetMapping("/toUpdateType/{typeId}")
    public String toUpdateTypePage(@PathVariable( value = "typeId") Long typId, Model model){
            Type type = typeService.getTypeById(typId);
            model.addAttribute("type",type);
            TotalsDto totals = getTotals();
            model.addAttribute("totals",totals);
            return "admin/updateType";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:26
    *@Param [type, model]
    *@Return java.lang.String
    */
    @PostMapping("/updateType")
    public String updateType(Type type,Model model){
        int i = typeService.updateType(type);
        if (i > 0){
            return "redirect:/admin/types";
        }
        Type result = typeService.getTypeById(type.getId());
        model.addAttribute("type",result);
        model.addAttribute("message","修改出现错误");
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "admin/updateType";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:26
    *@Param []
    *@Return java.lang.String
    */
    @GetMapping("/toAddType")
    public String toAddTypePage(Model model ){
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
        return "admin/addType1";
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:26
    *@Param [type, model]
    *@Return java.lang.String
    */
    @PostMapping("/addType")
    public String addType(Type type,Model model){
        int i = typeService.addType(type);
        if (i > 0) {
            return "redirect:/admin/types";
        }

        model.addAttribute("message","新增分类失败");
        TotalsDto totals = getTotals();
        model.addAttribute("totals",totals);
//        return "admin/addType";
        return "admin/addType1";
    }
    
    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/6 0:26
    *@Param [id]
    *@Return java.lang.String
    */
    @GetMapping("/deleteType/{typeId}")
    public String deleteType(@PathVariable(value = "typeId") Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }


    public TotalsDto getTotals(){
        totalsDto.setBlogTotals(blogService.blogTotal());
        totalsDto.setViewTotals(blogService.views());
        totalsDto.setCommentTotals(commentService.commentTotal());
        totalsDto.setMessageTotals(commentService.messageTotal());

        return totalsDto;
    }
}
