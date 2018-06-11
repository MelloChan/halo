package com.halo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
public class OrderProductDTO implements Serializable {

    private static final long serialVersionUID = 3304533797525751242L;
    @Min(1)
    private Integer proId;
    @NotEmpty
    private String imgUrl;
    @NotEmpty
    private String title;
    @Min(1)
    private Integer price;
    @Min(1)
    private Integer total;
    @Min(1)
    private Short number;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "proId=" + proId +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", number=" + number +
                '}';
    }
}
