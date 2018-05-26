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
     * @param phone 用户手机号
     * @return 用户id
     */
    Integer getIdByPhone(@Param("phone")String phone);
    /**
     * 通过手机号获取用户注册信息
     * @param phone 用户手机号
     * @return 用户注册信息类
     */
    UserRegistry getByPhone(@Param("phone")String phone);
}
