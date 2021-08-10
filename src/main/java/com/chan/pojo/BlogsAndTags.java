package com.chan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bronchan
 * @ClassName BlogsAndTags
 * @date 2021/8/5 14:59
 * @Version 1.0
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogsAndTags {
    Integer id;
    Long tagId;
    Long blogId;
}
