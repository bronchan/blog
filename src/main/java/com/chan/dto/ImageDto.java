package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bronchan
 * @ClassName ImageDto
 * @date 2021/8/2 14:05
 * @Version 1.0
 * @Description 返回图片markdown格式的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private int success;
    private String message;
    private String url;
}
