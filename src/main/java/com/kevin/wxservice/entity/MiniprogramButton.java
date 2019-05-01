package com.kevin.wxservice.entity;

import com.kevin.wxservice.service.WxConfig;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:    小程序菜单
 * @Author:         Kevin
 * @CreateDate:     2019/5/1 2:19
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/1 2:19
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class MiniprogramButton extends AbstractButon implements Serializable {

    //菜单类型
    private String type = "miniprogram";
    //自定义事件类型
    private String key;
    //小程序appid
    private String appid;
    //小程序页面路径
    private String pagepath;
    //小程序url
    private String url;

    public MiniprogramButton(String name, String key) {
        super(name);
        this.key = key;
        this.setAppid(WxConfig.MINIPROGRAM_APPID);
        this.setPagepath(WxConfig.MINIPROGRAM_PAGEPATH);
        this.setUrl(WxConfig.MINIPROGRAM_URL);
    }
}
