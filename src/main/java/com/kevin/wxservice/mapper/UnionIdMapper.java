package com.kevin.wxservice.mapper;

import com.kevin.wxservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @Description:    DOTO
 * @Author:         Kevin
 * @CreateDate:     2019/4/28 19:31
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/28 19:31
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Repository
@Mapper
public interface UnionIdMapper {

    /**
     * 插入unionid
     * @param user
     * @return
     */
    int insertUnionId(@Param("user") User user);

    /**
     * 查询已关注用户的unionid
     * @param openid
     * @return
     */
    String selectUnionId(@Param("openid") String openid);

    /**
     * 删除取消关注用户的unionid
     * @param openid
     * @return
     */
    int deleteUnionid(@Param("openid") String openid);
}
