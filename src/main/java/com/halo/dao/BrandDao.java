package com.halo.dao;

import com.halo.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/1
 */
@Mapper
public interface BrandDao {
    /**
     * 获取品牌
     */
    List<Brand>getBrand();

    /**
     * 根据分类id获取品牌
     */
    List<Brand>getBrandByCateId(@Param("cateId")Integer cateId);
}
