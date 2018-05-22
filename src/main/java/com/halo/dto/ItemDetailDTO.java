package com.halo.dto;

import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/5/22
 */
public class ItemDetailDTO implements Serializable {
    private static final long serialVersionUID = 5604644590202148848L;

    private String specification;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "ItemDetailsDTO{" +
                "specification='" + specification + '\'' +
                '}';
    }
}
