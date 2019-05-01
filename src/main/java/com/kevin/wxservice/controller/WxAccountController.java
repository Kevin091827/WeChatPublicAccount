package com.kevin.wxservice.controller;

import com.kevin.wxservice.service.WxAccountService;
import com.kevin.wxservice.util.ResponseUtils;
import com.kevin.wxservice.util.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:    微信公众号测试内网穿透
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 14:01
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 14:01
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@RequestMapping("/wx")
@Controller
@Slf4j
@ResponseBody
@CrossOrigin
public class WxAccountController {

    @Autowired
    private WxAccountService wxAccountService;

    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     *
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     *
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping(value = "/wxAccount")
    public void doGet(HttpServletRequest request, HttpServletResponse response){

        //获取微信服务器返回的参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info(signature+"\t"+timestamp+"\t"+nonce+"\t"+echostr);
        //验证签名
        if(wxAccountService.check(timestamp,nonce,signature)){
            //接入成功后返回echostr给微信服务器
            ResponseUtils.write(response,echostr);
            log.info("接入成功！");
        }else {
            log.info("接入失败！");
        }
    }

    @PostMapping(value = "/wxAccount")
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        log.info("post");
        response.setCharacterEncoding("UTF-8");
        try {
            //通过接收发送者发给公众号的消息或者关注公众号来获取发送者openid
            ServletInputStream inputStream = request.getInputStream();
            //将消息转为map
            Map<String,String> map =  XmlUtils.parseRequest(inputStream);
            log.info(map.toString());
            //获取发送者openid
            String openid =  map.get("FromUserName");
            log.info(openid);
            //获取消息类型
            String msgType = map.get("MsgType");
            //消息事件处理
            wxAccountService.dealWithMsg(response,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
