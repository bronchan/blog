package com.chan.service.impl;

import com.chan.dao.TagDao;
import com.chan.dto.BlogsOfTag;
import com.chan.pojo.Tag;
import com.chan.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bronchan
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;


    @Override
    public List<BlogsOfTag> getAllBlogsOfTag() {
        return tagDao.getAllBlogsOfTag();
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public int addTag(Tag tag) {
        return tagDao.addTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }


}
