package com.halo.service;

import com.halo.dto.ItemDTO;
import com.halo.dto.ItemDetailDTO;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
public interface ItemService {
    /**
     * 获取部分商品信息
     *
     * @return 商品信息列表
     */
    List<ItemDTO> getItems(Integer pageIndex, Integer pageCount);

    /**
     * 获取商品详情页信息
     *
     * @param proId 商品id
     * @return 商品详情
     */
    ItemDetailDTO getItemDetailByProId(Integer proId);

    /**
     * 通过分类id查询商品
     */
    List<ItemDTO> getItemsByCateId(Integer cateId);

    /**
     * 通过类型id查询商品
     */
    List<ItemDTO> getItemsByTypeId(Integer typeId);

    /**
     * 通过品牌id查询商品
     */
    List<ItemDTO> getItemsByBrandId(Integer brandId);
}
