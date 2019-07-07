package com.kevin.wxservice.message.content;

import com.alibaba.fastjson.annotation.JSONField;
import com.kevin.wxservice.message.base.BaseContent;
import com.kevin.wxservice.message.base.Keyword;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:TestMessage
 * @Description: TODO
 */
public class TestMessage extends BaseContent {

    /**
     * TestMessage：
     *
     * <li>主要用来封装消息实体</li>
     * <li>相当于请求参数中的data</li>
     */

    @JSONField(name = "keyword1")
    private Keyword tips;

    @JSONField(name = "keyword2")
    private Keyword pointInfo;

    @JSONField(name = "keyword3")
    private Keyword name;

    @JSONField(name = "keyword4")
    private Keyword phone;
}
