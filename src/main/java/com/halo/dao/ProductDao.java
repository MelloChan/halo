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
     *
     * @return 商品信息列表
     */
    List<Product> getItems(@Param("pageIndex") Integer pageIndex, @Param("pageCount") Integer pageCount);

    /**
     * 通过分类id查询商品
     */
    List<Product> getItemsByCateId(@Param("cateId") Integer cateId);

    /**
     * 通过类型id查询商品
     */
    List<Product> getItemsByTypeId(@Param("typeId") Integer typeId);

    /**
     * 通过品牌id查询商品
     */
    List<Product> getItemsByBrandId(@Param("brandId") Integer brandId);

    /**
     * 通过分类id与类型id查询
     */
    List<Product> getItemsByCateIdAndTypeId(@Param("cateId") Integer cateId, @Param("typeId") Integer typeId);

    /**
     * 通过分类di与品牌id查询
     */
    List<Product> getItemsByCateIdAndBrandId(@Param("cateId") Integer cateId, @Param("brandId") Integer brandId);

    /**
     * 通过名字模糊查找商品
     */
    List<Product> searchItemByName(@Param("name")String name);
    /**
     * 减库存
     */
    Integer updateMinusStockByProId(@Param("proId")Integer proId);
}
