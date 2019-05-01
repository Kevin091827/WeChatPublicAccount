package com.kevin.wxservice.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description:    消息实体类
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 17:17
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 17:17
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class Message implements Serializable {

    //接收者openid
    @XStreamAlias("ToUserName")
    private String toUserName;

    //发送者openid
    @XStreamAlias("FromUserName")
    private String fromUserName;

    //发送时间
    @XStreamAlias("CreateTime")
    private long createTime;

    //消息类型
    @XStreamAlias("MsgType")
    private String msgType;

    //消息内容
    @XStreamAlias("Content")
    private String content;

    public Message(Map<String,String> map,String content,String msgType) {
        //从接收到的消息中获取发送者
        this.toUserName = map.get("FromUserName");
        this.createTime = System.currentTimeMillis()/100;
        this.fromUserName = map.get("ToUserName");
        this.msgType = msgType;
        this.content = content;
    }
}
