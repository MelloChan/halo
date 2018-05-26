package com.halo.dto;

import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/5/22
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1004962297417618775L;

    private Integer userId;
    private String userName;
    private String userPhone;
    private String userAddress;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
