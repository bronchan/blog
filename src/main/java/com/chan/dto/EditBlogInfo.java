package com.chan.dto;

import com.chan.pojo.Type;
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
public class EditBlogInfo {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;

    private Type type;
}
