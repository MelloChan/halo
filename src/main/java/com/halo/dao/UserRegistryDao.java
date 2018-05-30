package com.halo.dao;

import com.halo.entity.UserRegistry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MelloChan
 * @date 2018/5/26
 */
@Mapper
public interface UserRegistryDao {
    /**
     * 通过手机号查找用户id
     *
     * @param phone 用户手机号
     * @return 用户id
     */
    Integer getIdByPhone(@Param("phone") String phone);

    /**
     * 通过手机号获取用户注册信息
     *
     * @param phone 用户手机号
     * @return 用户注册信息类
     */
    UserRegistry getByPhone(@Param("phone") String phone);



    /**
     * 插入用户注册信息
     *
     * @param userRegistry 用户注册信息类
     * @return 返回用户id
     */
    Integer insertUserRegistryInfo(UserRegistry userRegistry);

    /**
     * 更新密码
     *
     * @param salt 盐值
     * @param userId 用户id
     * @param newPwd  新密码
     * @return 影响条数
     */
    Integer updatePwdByUId(@Param("salt") String salt, @Param("newPwd") String newPwd, @Param("userId") String userId);
}
