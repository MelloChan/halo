package com.halo.dto;

import java.io.Serializable;

/**
 * 展示页面商品信息
 * @author MelloChan
 * @date 2018/5/16
 */
public class Item implements Serializable{

    private static final long serialVersionUID = -3486758677948381701L;
    private String img;
    private String name;
    private String title;
    private Long price;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
