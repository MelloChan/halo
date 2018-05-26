package com.halo.service;

/**
 * @author MelloChan
 * @date 2018/5/26
 */
public interface UserInfoService {
    /**
     * 通过手机号查找用户id
     * @param phone 用户手机号
     * @return 用户id
     */
    Integer getIdByPhone(String phone);
    /**
     * 通过手机号获取用户注册信息
     * @param phone 用户手机号
     * @param password 用户密码
     * @return 用户注册信息类
     */
    Integer verifyLoginInfo(String phone,String password);
}
