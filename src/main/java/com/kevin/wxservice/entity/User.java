package com.kevin.wxservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:    公众号用户实体类
 * @Author:         Kevin
 * @CreateDate:     2019/4/28 19:27
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/28 19:27
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
@Data
public class User implements Serializable {

    //用户昵称
    private String nickName;

    //用户unionId
    private String unionId;

    //用户openId
    private String openId;

    //开始关注时间（插入时间）
    private Date gmt_creat;

    //修改时间
    private Date gmt_modify;

    public User(String nickName, String unionId, String openId, Date gmt_creat, Date gmt_modify) {
        this.nickName = nickName;
        this.unionId = unionId;
        this.openId = openId;
        this.gmt_creat = gmt_creat;
        this.gmt_modify = gmt_modify;
    }
}
