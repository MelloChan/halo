package com.halo.dao;

import com.halo.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MelloChan
 * @date 2018/5/27
 */
@Mapper
public interface UserProfileDao {
    /**
     * 插入用户个人信息
     *
     * @param userProfile 用户个人信息类
     */
    void insertUserProfileInfo(UserProfile userProfile);

    /**
     * 通过用户Id获取用户信息
     *
     * @param userId 用户注册时赋值的唯一id
     * @return 用户个人信息
     */
    UserProfile getUserProfileInfoByUId(@Param("userId") String userId);

    /**
     * 通过用户id获取用户哈币数值
     * @param userId 用户id
     * @return 哈币
     */
    Integer getHaloCoinByUId(@Param("userId") String userId);

    /**
     * 更新哈币
     * @param number 更新的数值
     * @param userId 用户ID
     * @return 返回影响条数
     */
    Integer updateCoinByUId(@Param("number")Integer number,@Param("userId")String userId);
}
