package com.halo.dao;

import com.halo.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
@Mapper
public interface UserAddressDao {
    /**
     * 根据用户id查找用户地址信息列表
     */
    List<UserAddress>getByUId(@Param("userId")Integer userId);
}
