package com.halo.util;

import com.halo.config.properties.Ucpaas;

/**
 * @author MelloChan
 * @date 2018/5/25
 * 短信发送工具
 */
public class SmsUtil {
    private SmsUtil(){}

    /**
     * 短信单发验证
     * @param ucpaas 云之讯配置类
     * @return 结果json
     */
    public static String sendSms(Ucpaas ucpaas){
        String sid=ucpaas.getSid();
        String token=ucpaas.getToken();
        String appid=ucpaas.getAppid();
        String templateid=ucpaas.getTemplateid();
        String param=ucpaas.getParam();
        String mobile=ucpaas.getMobile();
        String url=ucpaas.getUrl();
        return HttpUtil.getInstance().requestPostByJson(url,GsonUtil.toJsonString(ucpaas));
    }
}
