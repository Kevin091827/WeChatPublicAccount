package com.kevin.wxservice.util;

import com.kevin.wxservice.entity.Message;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:    消息处理工具类
 * @Author:         Kevin
 * @CreateDate:     2019/5/1 15:05
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/1 15:05
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class MsgUtils {

    /**
     * 回复消息
     * @param response
     * @param content
     * @param msgType
     * @return
     */
    public static void replyMsg(HttpServletResponse response, String content, Map<String,String> map, String msgType) {

        //将消息转为xml
        String xml = XmlUtils.msgToXml(new Message(map,content,msgType));
        log.info(xml);
        //发送
        ResponseUtils.write(response,xml);
        log.info("回复成功！");
    }

    /**
     * 处理文本消息
     * @param response
     * @param map
     */
    public static void dealText(HttpServletResponse response ,Map<String, String> map) {
        //消息内容
        String info = map.get("Content");
        log.info("文本消息"+info);
        //根据规则回复用户消息 TODO
        replyMsg(response,"hello",map,"text");
    }

}
