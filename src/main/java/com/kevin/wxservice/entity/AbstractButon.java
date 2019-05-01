package com.kevin.wxservice.entity;

import lombok.Data;


/**
 * @Description:    菜单抽象
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 16:18
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 16:18
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class AbstractButon {

    //菜单名
    private String name;

    public AbstractButon(String name) {
        super();
        this.name = name;
    }
}
