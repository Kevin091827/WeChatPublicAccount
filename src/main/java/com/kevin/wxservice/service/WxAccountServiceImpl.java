package com.kevin.wxservice.service;

import com.alibaba.fastjson.JSONObject;
import com.kevin.wxservice.entity.*;
import com.kevin.wxservice.mapper.UnionIdMapper;
import com.kevin.wxservice.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @Description:    接入
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 15:44
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 15:44
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class WxAccountServiceImpl implements WxAccountService {

    @Autowired
    private UnionIdMapper unionIdMapper;

    //用于存储token
    private static AccessToken accessToken;

    /**
     * 验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    @Override
    public boolean check(String timestamp, String nonce, String signature) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[] {WxConfig.TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = Sha1Utils.sha1(str);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 请求微信接口获取accessToken，并封装
     * @return
     */
    private static void initAccessToken() {
        Map<String,String> params = new HashMap<>();
        //封装参数
        params.put("grant_type",WxConfig.GRANT_TYPE);
        params.put("appid",WxConfig.APPID);
        params.put("secret",WxConfig.SECRET);
        //请求微信接口获取acess_token
        String result = HttpClientUtils.doGet(WxConfig.ACCESS_TOKEN_URL,params);
        log.info(result);
        //将返回的结果转为JSONObject
        JSONObject jsonObject = JSONObject.parseObject(result);
        //获取access_token
        String access_token = jsonObject.getString("access_token");
        String expireIn  = jsonObject.getString("expires_in");
        log.info(access_token);
        accessToken = new AccessToken(access_token,expireIn);
    }

    /**
     * 获取accessToken
     * @return
     */
    private static String getAccessToken() {
        if(accessToken==null||accessToken.isExpired()) {
            initAccessToken();
        }
        return accessToken.getAccessToken();
    }

    /**
     * 获取unionID,并存入数据库
     * @param openid
     * @return
     */
    @Override
    public void getUnionid( String openid) {
        Map<String,String> params = new HashMap<>();
        //封装参数
        params.put("access_token",getAccessToken());
        params.put("openid",openid);
        params.put("lang","zh_CN");
        String result = HttpClientUtils.doGet(WxConfig.UNIONID_URL,params);
        log.info(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        //获取相关信息
        String unionid = jsonObject.getString("unionid");
        String nickName = jsonObject.getString("nickname");
        String openId = jsonObject.getString("openid");
        User user = new User(nickName,unionid,openId,new Date(),new Date());

        //判断数据库中是否已经存在该unionId对应的用户
        String oldUnionId = unionIdMapper.selectUnionId(openid);
        if(oldUnionId == null){
            //该用户没关注则插入数据库
            int i = unionIdMapper.insertUnionId(user);
            if(i > 0){
                log.info("插入成功");
            }else{
                log.error("插入失败");
            }
            log.info(unionid);
        }else{
            log.info("已经存在该用户,该用户已关注公众号！");
        }

    }

    /**
     * 删除取消关注用户的unionid记录
     * @param openid
     */
    @Override
    public void deleteUnionid(String openid) {

        int i = unionIdMapper.deleteUnionid(openid);
        if(i > 0){
            log.info("删除成功");
        }else{
            log.error("删除失败");
        }
    }

    /**
     * 消息事件处理
     * @param response
     * @param map
     */
    @Override
    public void dealWithMsg(HttpServletResponse response, Map<String, String> map) {

        //获取发送者openid
        String openid =  map.get("FromUserName");
        log.info(openid);
        //获取消息类型
        String msgType = map.get("MsgType");
        switch (msgType){
            //处理文本消息
            case "text":
                MsgUtils.dealText(response, map);
                break;
            //处理图片消息
            case "image":

                break;
            //处理语音消息
            case "voice":

                break;
            //处理视频消息
            case "video":

                break;
            //处理短视频
            case "shortvideo":

                break;
            //处理位置信息
            case "location":

                break;
            //处理链接信息
            case "link":

                break;
            //处理事件信息
            case "event":
                dealWithEvent(response, map);
                break;
            default:
                 //处理消息类型暂时不支持
                log.error("处理失败");
                break;
        }

    }

    /**
     * 处理事件信息
     * @param response
     * @param map
     */
    private void dealWithEvent(HttpServletResponse response ,Map<String, String> map) {
        String event = map.get("Event");
        //获取发送者openid
        String openid =  map.get("FromUserName");
        switch (event) {
            //订阅公众号，则获取该用户unionid放入数据库
            case "subscribe":
                   //拿到unionid
                   getUnionid(openid);
                   //回复用户关注成功消息
                   MsgUtils.replyMsg(response,"关注成功",map,"text");
                   break;
            //公众号取消订阅，则从数据库删除该用户unionid
            case "unsubscribe":
                    //删除unionid
                    deleteUnionid(openid);
                    break;
            //点击菜单事件
            case "CLICK":
                    break;
            default:
                break;
        }
    }

    /**
     * 创建菜单
     * @return
     */
    public void creatMenu() {

        //菜单对象
        Button btn = new Button();
        //第一个一级菜单
        btn.getButton().add(new MiniprogramButton("小程序入口", "miniprogram"));
        //第二个一级菜单
        btn.getButton().add(new ClickButton("一级跳转", "2"));
        //创建第三个一级菜单
        SubButton sb = new SubButton("有子菜单");
        //为第三个一级菜单增加子菜单
        sb.getSub_button().add(new ClickButton("传图", "31"));
        sb.getSub_button().add(new ClickButton("点击", "32"));
        sb.getSub_button().add(new ClickButton("网易新闻", "http://news.163.com"));
        //加入第三个一级菜单
        btn.getButton().add(sb);
        //转为json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(btn);
        //准备url
        String url = WxConfig.MENU_CREAT_URL.replace("ACCESS_TOKEN", getAccessToken());
        //发送请求
        String result = HttpClientUtils.doPost(url, jsonObject.toString());
        System.out.println(result);
    }

}
