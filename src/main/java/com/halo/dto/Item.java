package com.halo.dto;

import java.io.Serializable;

/**
 * 展示页面商品信息
 *
 * @author MelloChan
 * @date 2018/5/16
 */
public class Item implements Serializable {

    private static final long serialVersionUID = -3486758677948381701L;
    private Long proId;
    private String imgUrl;
    private String name;
    private String title;
    private Long price;

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImg() {
        return imgUrl;
    }

    public void setImg(String imgUrl) {
        this.imgUrl = imgUrl;
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
                "proId=" + proId +
                ", imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
