package com.kevin.wxservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    菜单组
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 16:18
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 16:18
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class Button {

    private List<AbstractButon> button = new ArrayList<>();

}
