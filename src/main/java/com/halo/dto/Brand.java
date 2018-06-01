package com.halo.dto;

import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/6/1
 */
public class Brand implements Serializable {
    private static final long serialVersionUID = -1004187904316206667L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
