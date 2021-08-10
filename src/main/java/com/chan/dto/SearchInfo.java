package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bronchan
 * @ClassName SearchInfo
 * @date 2021/8/1 16:10
 * @Version 1.0
 * @Description 从后台
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInfo {
    private String title;
    private Long typeId;
    private String recommend;
}
