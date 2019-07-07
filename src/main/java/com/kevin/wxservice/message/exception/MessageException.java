package com.kevin.wxservice.message.exception;

/**
 * @Description:    自定义错误 ---->模板消息错误
 * @Author:         Kevin
 * @CreateDate:     2019/7/7 23:28
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/7 23:28
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class MessageException extends RuntimeException {

    public MessageException(String str){
        super(str);
    }
}
