package com.halo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 展示页面商品信息列表传输对象
 * @author MelloChan
 * @date 2018/5/16
 */
public class ItemListDTO implements Serializable{

    private static final long serialVersionUID = -3134810709815072034L;

    private List<Item>items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemListDTO{" +
                "items=" + items +
                '}';
    }
}
