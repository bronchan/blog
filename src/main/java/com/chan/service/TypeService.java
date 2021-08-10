package com.chan.service;

import com.chan.dto.BlogsOfType;
import com.chan.pojo.Type;

import java.util.List;

/**
 * @author bronchan
 */
public interface TypeService {
    List<BlogsOfType> getAllBlogsOfType();
    List<Type> getAllTypes();
    Type getTypeById(Long id);
    int updateType(Type type);
    int addType(Type type);
    int deleteType(Long id);
}
