package com.chan.dto;

import com.chan.pojo.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bronchan
 * 标签下的博客信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogsOfTag {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
