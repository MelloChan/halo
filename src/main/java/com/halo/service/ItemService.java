package com.halo.service;

import com.halo.dto.Item;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
public interface ItemService {
    /**
     * 获取部分商品信息
     * @return 商品信息列表
     */
    List<Item> getItems(Integer pageIndex,Integer pageCount);
}
