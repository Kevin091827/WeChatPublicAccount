package com.kevin.wxservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:    click类型菜单
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 16:18
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 16:18
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class ClickButton extends AbstractButon implements Serializable {

    //菜单类型
    private String type = "click";
    //自定义事件类型
    private String key;

    public ClickButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
