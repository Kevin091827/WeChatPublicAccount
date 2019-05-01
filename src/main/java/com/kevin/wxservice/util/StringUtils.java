package com.kevin.wxservice.util;

/**
 * @Description:    字符串处理工具类
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 23:49
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 23:49
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class StringUtils {

    /**
     * 将字符串的首字母变大写
     * @param s
     * @return
     */
    public static String UpperFirstCharUtils(String s){
        char[] c = s.toCharArray();
        if( c[0]>'a' && c[0]<'z'){
            c[0] = (char)(c[0]-32);
        }
        return new String(c);
    }
}
