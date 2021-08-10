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
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;

    /**父评论昵称*/
    private String parentCommentNickname;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private Long baseCommentId;

}
