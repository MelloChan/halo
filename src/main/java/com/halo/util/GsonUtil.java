package com.halo.util;

import com.google.gson.Gson;
import com.qiniu.util.StringUtils;

/**
 * @author MelloChan
 * @date 2018/5/16
 */
public class GsonUtil {
    private static final Gson gson=new Gson();
    private GsonUtil(){}

    public static String toJsonString(Object object){
        return object==null?null:gson.toJson(object);
    }

    public static Object jsonToObject(String jStr,Class clazz){
        Object o=null;
        if(!StringUtils.isNullOrEmpty(jStr)){
            o=gson.fromJson(jStr,clazz);
        }
        return o;
    }
}
