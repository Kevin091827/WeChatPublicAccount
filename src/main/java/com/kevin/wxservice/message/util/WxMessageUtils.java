package com.kevin.wxservice.message.util;

import com.alibaba.fastjson.JSONObject;
import com.kevin.wxservice.entity.Message;
import com.kevin.wxservice.message.base.BaseMessage;
import com.kevin.wxservice.message.exception.MessageException;
import com.kevin.wxservice.service.WxAccountService;
import com.kevin.wxservice.service.WxAccountServiceImpl;
import com.kevin.wxservice.service.WxConfig;
import com.kevin.wxservice.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:    微信模板消息工具类
 * @Author:         Kevin
 * @CreateDate:     2019/7/8 0:52
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/8 0:52
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Component
@Slf4j
public class WxMessageUtils {

    public void sendTemplateMessage(BaseMessage baseMessage){
        //封装参数
        Map<String,String> params = new HashMap<>();
        params.put("access_token",WxAccountServiceImpl.getAccessToken());
        JSONObject jsonObject = JSONObject.parseObject(params.toString());
        //请求模板消息接口
        String resultJson = HttpClientUtils.doPostJson(WxConfig.TEMPLATEMESSAGE_URL,jsonObject.toJSONString());
        log.info("----------->"+resultJson);
        //判断是否请求成功
        checkSuccess(JSONObject.parseObject(resultJson),
                "Wechat return error：" + resultJson);

    }

    /**
     * 检查请求是否成功
     * @param bodyJSON
     * @param message
     */
    private static void checkSuccess(JSONObject bodyJSON, String message) {
        if (!isSuccess(bodyJSON)) {
            log.warn(message);
            throw new MessageException(bodyJSON.get("errmsg").toString());
        }
    }

    /**
     * 检查请求接口是否成功
     * @param bodyJSON
     * @return
     */
    private static boolean isSuccess(JSONObject bodyJSON) {
        if (bodyJSON.containsKey("errcode") && bodyJSON.containsKey("errmsg")) {
            if ((int) bodyJSON.get("errcode") != 0 && "ok".equals(bodyJSON.get("errmsg"))) {
                return false;
            }
        }
        return true;
    }
}
