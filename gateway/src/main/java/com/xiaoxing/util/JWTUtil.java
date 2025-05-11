package com.xiaoxing.util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;

import java.util.HashMap;

public class JWTUtil {
    /**
     * 密钥
     */
    public static final String key = "xiaoxing12306";

    /**
     * 获取 token
     */
    public static <T> String createToken(T obj){
        DateTime now = DateTime.now();
        DateTime offsetNew = now.offsetNew(DateField.HOUR, 24);
        HashMap<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT,now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT,offsetNew);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE,now);
        // 内容
        payload.put("data",obj);
        return cn.hutool.jwt.JWTUtil.createToken(payload,key.getBytes());
    }

    /**
     * 解析 token
     */
    public static boolean validate(String token){
        if (token==null||token.isBlank()){
            return false;
        }
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(key.getBytes());
        return jwt.validate(0);
    }

    /**
     * 获取内容
     */
    public static <T> T getDate(String token,Class<T> tClass){
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject jsonObject = (JSONObject) jwt.getPayload("data");
        return JSONUtil.toBean(jsonObject, tClass);
    }
}
