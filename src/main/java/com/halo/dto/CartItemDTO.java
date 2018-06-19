package com.halo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/6/2
 */
public class    CartItemDTO implements Serializable {
    private static final long serialVersionUID = 6966010392610826703L;
    @Min(1)
    private Integer id;
    @NotEmpty
    private String imgUrl;
    @NotEmpty
    private String description;
    @Min(1)
    private Integer price;
    @Min(1)
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItemDTO{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
