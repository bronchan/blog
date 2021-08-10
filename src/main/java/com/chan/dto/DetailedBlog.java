package com.chan.dto;

import com.chan.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bronchan
 * 博客详细页
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedBlog {
    private Long id;
    private String firstPicture;

    private String flag;
    private String title;
    private String content;

    private Integer views;
    private Date updateTime;

    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;

    private String nickname;
    private String avatar;

    private String typeName;

    private List<Tag> tags = new ArrayList<>();
}
