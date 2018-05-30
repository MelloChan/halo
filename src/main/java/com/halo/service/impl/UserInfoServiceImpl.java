package com.halo.service.impl;

import com.halo.dao.UserProfileDao;
import com.halo.dao.UserRegistryDao;
import com.halo.dto.UserProfileInfoDTO;
import com.halo.dto.UserRegisterInfoDTO;
import com.halo.entity.UserProfile;
import com.halo.entity.UserRegistry;
import com.halo.service.UserInfoService;
import com.halo.util.DigestUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MelloChan
 * @date 2018/5/26
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRegistryDao userRegistryDao;
    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public Integer getIdByPhone(@Param("phone") String phone) {
        return userRegistryDao.getIdByPhone(phone);
    }

    @Override
    public Integer verifyLoginInfo(@Param("phone") String phone, String password) {
        UserRegistry userRegistry = userRegistryDao.getByPhone(phone);
        if (userRegistry != null && DigestUtil.verify(password, userRegistry.getSalt(), userRegistry.getPwd())) {
            return userRegistry.getId();
        }
        return -1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertUserInfo(UserRegisterInfoDTO userRegisterInfoDTO) {
        // 插入注册信息
        UserRegistry userRegistry = new UserRegistry();
        userRegistry.setPhone(userRegisterInfoDTO.getPhone());
        String salt = DigestUtil.generateSalt();
        String pwd = DigestUtil.sha256(userRegisterInfoDTO.getPwd() + salt);
        userRegistry.setPwd(pwd);
        userRegistry.setSalt(salt);
        Integer uid = userRegistryDao.insertUserRegistryInfo(userRegistry);

        // 插入个人信息
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(uid);
        userProfile.setUsername(userRegisterInfoDTO.getUsername());
        userProfile.setPhone(userRegisterInfoDTO.getPhone());
        userProfileDao.insertUserProfileInfo(userProfile);

        return uid;
    }

    @Override
    public UserProfileInfoDTO getUserProfileInfoByUId(@Param("userId") String userId) {
        UserProfile userProfile = userProfileDao.getUserProfileInfoByUId(userId);
        return new UserProfileInfoDTO(
                userProfile.getUsername(), userProfile.getAvatar(), userProfile.getSecurityLevel(), userProfile.getEmail(),
                userProfile.getPhone(), userProfile.getPwdProtection());
    }


    @Override
    public Integer getHaloCoinByUId(@Param("userId") String userId) {
        return userProfileDao.getHaloCoinByUId(userId);
    }

    @Override
    public boolean updateCoinByUId(@Param("number") Integer number, @Param("userId") String userId) {
        return userProfileDao.updateCoinByUId(number, userId) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateAvatarById(@Param("imgUrl") String imgUrl, @Param("userId") String userId) {
//        UploadUtil.uploadToQiNiuYun()
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePwdByUid(String newPwd, String userId) {
        String newSalt= DigestUtil.generateSalt();
        String password=DigestUtil.sha256(newSalt+newPwd);
      return userRegistryDao.updatePwdByUId(newSalt,password,userId)!=null;
    }
}
