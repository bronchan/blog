package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author bronchan
 * @ClassName WangEditor
 * @date 2021/8/4 19:47
 * @Version 1.0
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WangEditor {

    /**错误代码，0 表示没有错误*/
    private Integer errno;
    /**已上传的图片路径*/
    private String[] data;


    public WangEditor(String[] data) {
        this.errno = 0;
        this.data = data;
    }

}
