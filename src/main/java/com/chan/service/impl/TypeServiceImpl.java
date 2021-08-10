package com.chan.service.impl;

import com.chan.dao.TypeDao;
import com.chan.dto.BlogsOfType;
import com.chan.pojo.Type;
import com.chan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bronchan
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<BlogsOfType> getAllBlogsOfType() {
         return typeDao.getAllBlogsOfType();
    }

    @Override
    public List<Type> getAllTypes() {
        return typeDao.getAllTypes();
    }

    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public int addType(Type type) {
        return typeDao.addType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
