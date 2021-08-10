package com.chan.dto;

import com.chan.pojo.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bronchan
 * 分类下的博客
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogsOfType {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
