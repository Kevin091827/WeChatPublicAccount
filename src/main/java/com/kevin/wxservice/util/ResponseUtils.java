package com.kevin.wxservice.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:    响应工具类
 * @Author:         Kevin
 * @CreateDate:     2019/4/30 1:04
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/30 1:04
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class ResponseUtils {

    /**
     * 接入微信服务器
     * @param response
     * @param result
     */
    public static void write(HttpServletResponse response,String result){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(result);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
