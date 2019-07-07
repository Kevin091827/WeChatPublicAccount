package com.kevin.wxservice.service;

/**
 * @Description:    公众号相关配置
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 15:49
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 15:49
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public interface WxConfig {

    //公众号平台中设置的TOKEN
    final String TOKEN = "abc";

    //获取access_token url
    final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    //grant_type	获取access_token填写client_credential
    final String GRANT_TYPE = "client_credential";

    // appid	第三方用户唯一凭证
    final String APPID = "";

    // secret	第三方用户唯一凭证密钥，即appsecret
    final String SECRET = "";

    //获取用户基本信息（unionID）
    final String UNIONID_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    //创建菜单url
    final String MENU_CREAT_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //小程序appid
    String MINIPROGRAM_APPID = "";

    //小程序页面路径
    String MINIPROGRAM_PAGEPATH = "";

    //小程序url
    String MINIPROGRAM_URL = "";


}
