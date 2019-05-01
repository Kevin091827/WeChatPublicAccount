package com.kevin.wxservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    子菜单
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 16:19
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 16:19
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class SubButton  extends AbstractButon {

    private List<AbstractButon> sub_button = new ArrayList<>();

    public SubButton(String name) {
        super(name);
    }
}
