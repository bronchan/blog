package com.chan.dto;

import com.chan.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bronchan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentComments {
    private Long id;
    private String nickname;
    private String email;
    private String content;

    private String parentCommentNickname;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private Long baseCommentId;


    //回复评论

    private List<Comment> SunComments = new ArrayList<>();
}
