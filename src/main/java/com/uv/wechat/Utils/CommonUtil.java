package com.uv.wechat.Utils;
/*
 * @author liuwei
 * @date 2018/9/8 11:50
 * 消息xml转换
消息：
文本消息——text，语音消息——voice，图片消息——image，
视频消息——video，链接消息——link，位置消息——location，
消息视频消息——shortvideo
事件：
关注——subscribe，取消关注——unsubscribe，上传地理位置——location
菜单点击——点击菜单获取消息时触发click/点击菜单跳转链接时触发view
扫描带参数二维码——未关注时触发subscribe/已关注时触发scan
 */

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.uv.wechat.Entity.ChatMessage;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CommonUtil {

    /*
    消息：
    文本消息——text，语音消息——voice，图片消息——image，
    视频消息——video，链接消息——link，位置消息——location，
    消息视频消息——shortvideo
    事件：
    关注——subscribe，取消关注——unsubscribe，上传地理位置——location
    菜单点击——点击菜单获取消息时触发click/点击菜单跳转链接时触发view
    扫描带参数二维码——未关注时触发subscribe/已关注时触发scan
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_SHORTVIDEO = "shortvideo";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "click";
    public static final String MESSAGE_VIEW = "view";
    public static final String MESSAGE_SCAN = "scan";

    //本人ID
    public static final String USERME = "opRpe1SGSNg6v75UIYnOs2waj8Iw";
    //初始化菜单url
    public static final String MENU_URL = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    //获取文章列表
    public static final String MSGLIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";

    /**
     * xml转换成map
     */
    public static Map<String, String> xml2Map(HttpServletRequest request) {
        SAXReader saxReader = new SAXReader();
        Map<String, String> messageMap = Maps.newHashMap();
        try {
            @Cleanup InputStream is = request.getInputStream();
            Document document = saxReader.read(is);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                messageMap.put(element.getName(), element.getText());
            }
            return messageMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageMap;
    }

    /**
     * 消息转xml
     */
    public static String object2Xml(ChatMessage message) {
        XStream xStream = new XStream();
        xStream.alias("xml", ChatMessage.class);
        return xStream.toXML(message);
    }

    /**
     * 发送的信息
     */
    public static String sendMsg(Map<String, String> map, String msgType, String content) {
        ChatMessage message = new ChatMessage();
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        //原来【接收消息用户】变为回复时【发送消息用户】
        message.setFromUserName(ToUserName);
        message.setToUserName(FromUserName);
        message.setMsgType(msgType);
        message.setContent(content);
        //创建当前时间为消息时间
        message.setCreateTime(new Date().getTime() + "");
        return object2Xml(message);
    }

    /**
     * 封装url请求
     *
     * @param method 请求方式
     * @param params 请求参数
     */
    public static JSONObject getUrlResource(String url, HttpMethod method, MultiValueMap<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;
        if (method.matches("GET")) {
            responseEntity = restTemplate.getForEntity(url, String.class);
        } else if (method.matches("POST")) {
            HttpHeaders headers = new HttpHeaders();
            //设置类型
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers, params);
            //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            if (params != null) {
                responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            } else {
                responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            }
            if ("200".equals(responseEntity.getStatusCode().toString())) {
                JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
                return jsonObject;
            }
        }
        return null;
    }

}

