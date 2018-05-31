package com.halo.dao;

import com.halo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@Mapper
public interface ProductDao {
    /**
     * 获取部分商品信息
     * @return 商品信息列表
     */
    List<Product> getItems(@Param("pageIndex")Integer pageIndex,@Param("pageCount")Integer pageCount);
}
