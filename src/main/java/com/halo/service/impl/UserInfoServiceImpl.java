package com.halo.service.impl;

import com.halo.config.properties.QiNiu;
import com.halo.dao.UserProfileDao;
import com.halo.dao.UserRegistryDao;
import com.halo.dto.UserProfileInfoDTO;
import com.halo.dto.UserRegisterInfoDTO;
import com.halo.entity.UserProfile;
import com.halo.entity.UserRegistry;
import com.halo.service.UserInfoService;
import com.halo.util.DigestUtil;
import com.halo.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;

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
    @Autowired
    private QiNiu qiNiu;

    @Override
    public Integer getIdByPhone(String phone) {
        return userRegistryDao.getIdByPhone(phone);
    }

    @Override
    public Integer verifyLoginInfo(String phone, String password) {
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
    public UserProfileInfoDTO getUserProfileInfoByUId(String userId) {
        UserProfile userProfile = userProfileDao.getUserProfileInfoByUId(userId);
        return new UserProfileInfoDTO(
                userProfile.getUsername(), userProfile.getAvatar(), userProfile.getSecurityLevel(), userProfile.getEmail(),
                userProfile.getPhone(), userProfile.getPwdProtection());
    }


    @Override
    public Integer getHaloCoinByUId(String userId) {
        return userProfileDao.getHaloCoinByUId(userId);
    }

    @Override
    public boolean updateCoinByUId(Integer number, String userId) {
        return userProfileDao.updateCoinByUId(number, userId) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateAvatarById(Part part, String userId) throws IOException {
        String imgUrl;
        try (BufferedInputStream in = new BufferedInputStream(part.getInputStream())) {
            int size = (int) part.getSize();
            byte[] buffer = new byte[size];
            int i = in.read(buffer);
            while ((i > 0)) {
                i = in.read(buffer);
            }
            imgUrl = UploadUtil.uploadToQiNiuYun(qiNiu, buffer);
            userProfileDao.updateAvatarById(imgUrl, userId);
        }
        return imgUrl;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePwdByUid(String newPwd, String userId) {
        String newSalt = DigestUtil.generateSalt();
        String password = DigestUtil.sha256(newSalt + newPwd);
        return userRegistryDao.updatePwdByUId(newSalt, password, userId) != null;
    }
}
