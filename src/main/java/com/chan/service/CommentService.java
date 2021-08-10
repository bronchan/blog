package com.chan.service;

import com.chan.dto.ParentComments;
import com.chan.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    List<ParentComments> getParentComments(Long blogId);

    List<Comment> getCommentsByDidId(Long parentId, Long blogId);

    int addComment(Comment comment);

    List<ParentComments> getParentMessages();

    List<Comment> getMessagesByDidId(Long parentId);

    int commentTotal();

    int messageTotal();


}
