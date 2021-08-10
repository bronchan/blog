package com.chan.dao;

import com.chan.dto.BlogsOfType;
import com.chan.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao {
//    获取分类和分类里文章数
    List<BlogsOfType> getAllBlogsOfType();
//    获取所以分类
    List<Type> getAllTypes();
//    根据id获取分类
    Type getTypeById(Long id);
//    修改分类
    int updateType(Type type);
    int addType(Type type);
    int deleteType(Long id);
}
