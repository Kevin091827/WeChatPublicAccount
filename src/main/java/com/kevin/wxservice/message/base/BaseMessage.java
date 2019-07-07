package com.kevin.wxservice.message.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:    消息json
 * @Author:         Kevin
 * @CreateDate:     2019/7/7 22:58
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/7 22:58
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage {

    /**
     * accessToken: 接口调用凭证
     * openId：接受者 openId
     * templateId: 模板消息id
     * page: 要跳转的页面
     * formId: submit提交的formId
     * data: 模板内容
     */
//请求示例：-----------------------------------------------
//    {
//        "touser": "OPENID",
//            "template_id": "TEMPLATE_ID",
//            "page": "index",
//            "form_id": "FORMID",
//            "data": {
//        "keyword1": {
//            "value": "339208499"
//        },
//        "keyword2": {
//            "value": "2015年01月05日 12:30"
//        },
//        "keyword3": {
//            "value": "腾讯微信总部"
//        } ,
//        "keyword4": {
//            "value": "广州市海珠区新港中路397号"
//        }
//    },
//        "emphasis_keyword": "keyword1.DATA"
//    }
//---------------------------------------------------------

    @JSONField(name = "touser")
    private String openId;

    @JSONField(name = "template_id")
    private String template_id;

    @JSONField(name = "page")
    private String page;

    @JSONField(name = "form_id")
    private String form_id;

    private BaseContent data;

    @JSONField(name = "emphasis_keyword")
    private String emphasis_keyword;
}
