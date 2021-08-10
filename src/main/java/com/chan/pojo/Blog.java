package com.chan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author bronchan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private int recommend;
    private Date createTime;
    private Date updateTime;
    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;
    //获取多个标签的id

    private String tagIds;
    private String description;

}
