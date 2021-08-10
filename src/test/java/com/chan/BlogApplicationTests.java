package com.chan;

import com.chan.dao.BlogsAndTagsDao;
import com.chan.dto.EditBlogInfo;
import com.chan.dto.SearchInfo;
import com.chan.dto.WangEditor;
import com.chan.pojo.BlogsAndTags;
import com.chan.pojo.Tag;
import com.chan.pojo.Type;
import com.chan.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
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
    BlogsAndTagsServiceImpl blogsAndTagsService;

    @Test
    void contextLoads() {
        SearchInfo searchInfo = new SearchInfo("", 4L, null);
        List<EditBlogInfo> editBlogInfoBySearchInfo = blogService.getEditBlogInfoBySearchInfo(searchInfo);
        toOut(Collections.singletonList(editBlogInfoBySearchInfo));
    }

    private void toOut(List<Object> objects){
        for (Object object :objects) {
            System.out.println(object);
        }
    }

    @Test
    void test2(){
        BlogsAndTags blogsAndTags = new BlogsAndTags();
        blogsAndTags.setTagId(3L);
        blogsAndTags.setBlogId(15661651L);
        int i = blogsAndTagsService.addBlogsAndTags(blogsAndTags);
        System.out.println(i);
    }
}
