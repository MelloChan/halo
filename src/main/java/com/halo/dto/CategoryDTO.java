package com.halo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/17
 */
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 6032728683904816818L;

    private List<String> category;
    private List<String> brand;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "category=" + category +
                ", brand=" + brand +
                '}';
    }
}
