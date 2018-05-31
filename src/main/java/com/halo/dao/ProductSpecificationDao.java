package com.halo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@Mapper
public interface ProductSpecificationDao {
    String getSpecificationByProId(@Param("proId")String proId);
}
