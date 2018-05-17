package com.halo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 通用商品详情页面传输对象
 *
 * @author MelloChan
 * @date 2018/5/17
 */
public class CommonItemDetailsDTO implements Serializable {
    private static final long serialVersionUID = 8571547474636928465L;

    private String name;
    private String title;
    private Long price;
    private List<String> color;
    private List<String> imgUrl;

    public CommonItemDetailsDTO() {
    }

    public CommonItemDetailsDTO(String name, String title, Long price, List<String> color, List<String> imgUrl) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.color = color;
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

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CommonItemDetailsDTO{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", color=" + color +
                ", imgUrl=" + imgUrl +
                '}';
    }
}
