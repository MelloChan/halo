package com.halo.service;

import com.halo.config.properties.Ucpaas;
import com.halo.redis.RedisUtil;
import com.halo.util.SmsUtil;
import com.halo.util.VerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MelloChan
 * @date 2018/5/26
 */
@Service
public class AuthService {
    @Autowired
    private Ucpaas ucpaas;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送短信验证码 本地验证码存入缓存 60s内过期
     *
     * @param phone 用户手机号
     * @return 发送成功否
     */
    public boolean sendSmsCode(String phone) {
        ucpaas.setMobile(phone);
        ucpaas.setTemplateid(ucpaas.getTemplateid().substring(0, 6));
        String code = VerifyCodeGenerator.getFourVerifyCode();
        redisUtil.add(phone, 60L, code);
        ucpaas.setParam(code);
        String json = SmsUtil.sendSms(ucpaas);
        int okIdx = json.indexOf("OK");
        return "OK".equals(json.substring(okIdx, okIdx + 2));
    }

    /**
     * 验证手机号码是否注册过
     *
     * @param phone 用户手机号
     * @return 是否注册
     */
    public boolean verifyPhone(String phone) {
        return userInfoService.getIdByPhone(phone) != null;
    }

    /**
     * 验证手机验证码是否正确
     *
     * @param phone 用户手机号
     * @param code  用户发来的验证码
     * @return 是否正确
     */
    public boolean verifyCode(String phone, String code) {
        return code.equals(redisUtil.get(phone));
    }
}
