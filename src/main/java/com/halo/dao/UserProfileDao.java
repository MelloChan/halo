package com.halo.dao;

import com.halo.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;

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
}
