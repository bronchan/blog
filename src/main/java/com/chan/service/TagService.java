package com.chan.service;

import com.chan.dto.BlogsOfTag;
import com.chan.pojo.Tag;

import java.util.List;

/**
 * @author bronchan
 */
public interface TagService {
    List<BlogsOfTag> getAllBlogsOfTag();
    List<Tag> getAllTags();
    int addTag(Tag tag);
    Tag getTagById(Long id);
    int updateTag(Tag tag);
    int deleteTag(Long id);
}
