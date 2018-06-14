package com.halo.dao;

import com.halo.dto.ItemInfoDTO;
import com.halo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
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
     * 减库存
     */
    Integer updateMinusStockByProId(@Param("proId")Integer proId);

    /**
     * 后台获取商品信息
     */
    List<ItemInfoDTO> getItemsInfo(@Param("pageIndex") Integer pageIndex, @Param("pageCount") Integer pageCount);

    /**
     * 后台删除商品表中指定商品
     */
    void deleteProductById(@Param("id") Integer id);

    /**
     * 后台删除指定商品的详细描述
     */
    void deleteProductDetailById(@Param("id") Integer id);

    /**
     * 后台删除指定商品的图片
     */
    void deleteProductImageById(@Param("id") Integer id);

    /**
     * 删除多个商品信息
     */
    void deleteMultiProductsById(ArrayList<Integer> idList);

    /**
     * 删除多个商品相应的详细描述
     */
    void deleteMultiProductDetailsById(ArrayList<Integer> idList);

    /**
     * 删除多个商品相应的图片
     */
    void deleteMultiProductImagesById(ArrayList<Integer> idList);

    /**
     * 更改商品信息
     */
    void updateProductInfoById(@Param("id") Integer id, @Param("name") String name, @Param("num") Integer num,
                               @Param("price") Integer price);

    /**
     * 获取商品库存
     */
    Integer getStockById(@Param("id") Integer id);

    /**
     * 通过类型和名字获取商品信息
     */
    List<ItemInfoDTO> getItemsInfoByTypeAndName(@Param("type") String type, @Param("name") String name, @Param("pageIndex") Integer paeIndex,
                                         @Param("pageCount") Integer pageCount);

}
