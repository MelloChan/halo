package com.halo.service.impl;

import com.halo.dao.UserRegistryDao;
import com.halo.entity.UserRegistry;
import com.halo.service.UserInfoService;
import com.halo.util.DigestUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MelloChan
 * @date 2018/5/26
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRegistryDao userRegistryDao;

    @Override
    public Integer getIdByPhone(@Param("phone") String phone) {
        return userRegistryDao.getIdByPhone(phone);
    }

    @Override
    public Integer verifyLoginInfo(@Param("phone") String phone, String password) {
        UserRegistry userRegistry = userRegistryDao.getByPhone(phone);
        boolean flag = DigestUtil.verify(password, userRegistry.getSalt(), userRegistry.getPwd());
        if (flag) {
            return userRegistry.getId();
        } else {
            return null;
        }
    }
}
