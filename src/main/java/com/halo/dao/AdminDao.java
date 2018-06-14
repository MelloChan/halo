package com.halo.dao;

import com.halo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author SAIKAII
 * @date 2018/6/6
 */

@Mapper
public interface AdminDao {

    /**
     * 获取指定管理员信息
     *
     * @param username 管理员用户名
     * @return 管理员的信息
     */
    Admin getAdminInfoByUsername(@Param("username") String username);


    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     */
    void insertAdminInfo(Admin admin);

}
