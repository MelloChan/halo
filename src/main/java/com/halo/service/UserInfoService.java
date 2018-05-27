package com.halo.service;

import com.halo.dto.UserRegisterInfoDTO;

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
     * 验证用户手机号与密码
     * @param phone 用户手机号
     * @param password 用户密码
     * @return 用户id
     */
    Integer verifyLoginInfo(String phone,String password);

    /**
     * 用户注册
     * @param userRegisterInfoDTO 用户注册信息 手机号+密码+用户名
     * @return 用户id
     */
    Integer insertUserInfo(UserRegisterInfoDTO userRegisterInfoDTO);
}
