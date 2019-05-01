package com.kevin.wxservice.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:    接入
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 15:49
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 15:49
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public interface WxAccountService {

    /**
     * 检查是否接入成功
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    boolean check(String timestamp, String nonce, String signature);

    /**
     * 获取用户unionID
     * @param openid
     * @return
     */
    void getUnionid(String openid);


    /**
     * 删除取消关注用户unionid
     * @param openid
     */
    void deleteUnionid(String openid);

    /**
     * 消息事件处理
     * @param response
     * @param map
     */
    void dealWithMsg(HttpServletResponse response, Map<String, String> map);
}
