package com.kevin.wxservice.entity;

import lombok.Data;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AccessToken
 * @Description: TODO
 */
@Data
public class AccessToken {

    private String accessToken;
    private long expireTime;

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
        expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    /**
     * 判断token是否过期
     * @return
     * by 罗召勇 Q群193557337
     */
    public boolean isExpired() {
        return System.currentTimeMillis()>expireTime;
    }
}
