package com.chan.service.impl;

import com.chan.dao.BlogDao;
import com.chan.dao.CommentDao;
import com.chan.dto.ParentComments;
import com.chan.pojo.Comment;
import com.chan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author bronchan
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<ParentComments> getParentComments(Long blogId) {
        return commentDao.getParentComments(blogId);
    }

    @Override
    public List<Comment> getCommentsByDidId(Long parentId, Long blogId) {
        return commentDao.getCommentsByDidId(parentId,blogId);
    }

    @Override
    public int addComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentDao.addComment(comment);
    }

    @Override
    public List<ParentComments> getParentMessages() {
        return commentDao.getParentMessages();
    }

    @Override
    public List<Comment> getMessagesByDidId(Long parentId) {
        return commentDao.getMessagesByDidId(parentId);
    }

    @Override
    public int commentTotal() {
        return commentDao.commentTotal();
    }

    @Override
    public int messageTotal() {
        return commentDao.messageTotal();
    }
}
