package com.kevin.wxservice.message.config;

/**
 * @Description:    存放模板消息id
 * @Author:         Kevin
 * @CreateDate:     2019/7/7 23:24
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/7 23:24
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public enum TemplateIdEnum {

    /**
     * TemplateIdEnum
     * <li>存放模板消息ID</li>
     */

    TEXTMSG_ID("");

    private String TEMPLATE_ID;

    TemplateIdEnum(String TEMPLATE_ID){
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public static String getTemplateId(TemplateIdEnum type){
        return type.TEMPLATE_ID;
    }

}

