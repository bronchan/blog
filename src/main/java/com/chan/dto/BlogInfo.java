package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author bronchan
 * 获取博客的简单描述信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {

    private Long id;
    private String description;
    private String title;
    private Date updateTime;
    private Integer views;
    private String firstPicture;

//    type
    private String typeName;

//    user
    private String avatar;
    private String nickname;


}
