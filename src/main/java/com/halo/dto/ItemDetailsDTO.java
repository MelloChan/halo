package com.halo.dto;

import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/5/22
 */
public class ItemDetailsDTO implements Serializable {
    private static final long serialVersionUID = 5604644590202148848L;

    private String specification;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "ItemDetailsDTO{" +
                "specification='" + specification + '\'' +
                '}';
    }
}
