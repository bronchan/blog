package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bronchan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {

    private Long id;
    private String title;
    private int recommend;
}
