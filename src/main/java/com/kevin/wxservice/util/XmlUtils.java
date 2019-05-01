package com.kevin.wxservice.util;

import com.kevin.wxservice.entity.Message;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:    解析xml数据包为Map
 * @Author:         Kevin
 * @CreateDate:     2019/4/27 17:08
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/27 17:08
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class XmlUtils {

    /**
     * 解析xml数据包，封装为Map
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点的所有的子节点
            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将消息对象转为xml数据包
     *
     * @param message
     * @return
     */
    public static String msgToXml(Message message) {
        XStream stream = new XStream();
        //设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(message.getClass());
        String xml = stream.toXML(message);
        return xml;
    }

}
