# 微信公众号模块

### 一.模块简介

该微信公众号应用以下框架
    
 * springboot 2.1.4.
 * mybatis 1.3.1

配合 [微信公众号开发文档](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432)
搭建
### 二.模块分层

```yaml
com:
  kevin:
    wxservice:
      controller: 控制层    
      entity: 实体层
      mapper: 持久层
      service: 业务层
      util: 工具包
```

### 三.分层介绍

#### 1.控制层
```yaml
controller:
    WxAccountController
```
WxAccountController中映射url和微信公众平台上的服务器地址相对应

第一次配置接入时，在微信公众平台上完成开发者配置后（服务器地址，token，消息加解密密钥，方式）,点击提交后 
微信服务器会向我们配置好的服务器url发送一条get请求，而我们要做的就是验证该消息是不是来源于微信服务器,
若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功。

配置完成成为开发者后，接下来和微信服务器交互方式都是post请求

#### 2.业务层
```yaml
WxAccountServiceImpl:
    methodName:
      check:
        effect:"检查接入微信服务器是否成功"
      getAccessToken:
        effect:"获取接口使用权限参数accessToken" 
      getUnionid:
        effect:"获取已关注用户unionid且放入数据库"
      deleteUnionid: 
        effect:"删除取消关注用户的unionid数据库记录"   
      dealWithMsg：
        effect:"消息事件处理"
      dealWithEvent:
        effect:"处理事件消息"  
```
#### 3.工具包
```yaml
utli:
  HttpClientUtils:
    effect:"接口请求工具"
  MsgUtils:
    effect:"各类消息处理回复工具，如需添加新类型消息回复（如回复图片消息）则可在本类扩展，业务层调用"
  ResponseUtils:
    effect:"响应处理工具类,主要是程序后台给微信服务器响应" 
  Sha1Utils:
    effect:"sha1加密工具类" 
  StringUtlis:
    effect:"字符串处理工具类"
  xmlUtils:
    effect:"xml解析和封装工具类"      
```

#### 4.相关配置信息
和本公众号相关的配置信息都位于Service\WxConfig下,可根据需要修改
