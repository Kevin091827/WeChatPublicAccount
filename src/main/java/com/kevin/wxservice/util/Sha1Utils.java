package com.kevin.wxservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:    sha1加密工具类
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 15:42
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 15:42
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class Sha1Utils {

    /**
     * sha1加密
     * @param src
     * @return
     */
    public static String sha1(String src) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for(byte b:digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
