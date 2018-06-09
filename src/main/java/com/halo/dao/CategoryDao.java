package com.halo.dao;

import com.halo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@Mapper
public interface CategoryDao {
    /**
     * 获取分类属性
     */
    List<Category> getCate();
}
