package com.chan.dao;

import com.chan.dto.BlogsOfTag;
import com.chan.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bronchan
 */
@Mapper
@Repository
public interface TagDao {
//    获取所有标签和该标签里文章数
    List<BlogsOfTag> getAllBlogsOfTag();
//    获取所有便签信息
    List<Tag> getAllTags();
//    增加标签
    int addTag(Tag tag);
//    通过id获取标签
    Tag getTagById(Long id);
//    修改标签
    int updateTag(Tag tag);
//    删除标签
    int deleteTag(Long id);
}
