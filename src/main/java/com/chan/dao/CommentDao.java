package com.chan.dao;

import com.chan.dto.ParentComments;
import com.chan.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bronchan
 */
@Mapper
@Repository
public interface CommentDao {
    List<ParentComments> getParentComments(@Param("id") Long blogId);
    List<Comment> getCommentsByDidId(@Param("parentId") Long ParentId ,@Param("blogId") Long blogId);
    int addComment(Comment comment);
    List<ParentComments> getParentMessages();
    List<Comment> getMessagesByDidId(@Param("parentId") Long parentId);
    int commentTotal();
    int messageTotal();

}
