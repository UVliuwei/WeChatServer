package com.uv.wechat.Utils;
/*
 * @author liuwei
 * @date 2018/9/8 14:01
 * 消息分类处理
 */

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandleUtil {

    /**
     * 处理文字
     */
    public static String handleText(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        if (CommonUtil.MESSAGE_TEXT.equals(msgType)) {
            String reMsg = "您好:\n"
                + "我是" + toUserName + "\n"
                + "你是" + fromUserName + "\n"
                + "发送的消息：" + content;
            String msg = CommonUtil.sendMsg(msgMap, CommonUtil.MESSAGE_TEXT, reMsg);
            log.info(content);
            return msg;
        }
        log.info("text");
        return "";
    }

    /**
     * 处理图片
     */
    public static String handleImage(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String picUrl = msgMap.get("PicUrl");
        String mediaId = msgMap.get("MediaId");
        String msgId = msgMap.get("MsgId");
        log.info("image");
        return "";
    }

    /**
     * 处理声音
     */
    public static String handleVoice(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String mediaId = msgMap.get("MediaId");
        String format = msgMap.get("Format"); //语音格式
        String recognition = msgMap.get("Recognition"); //语音识别结果
        String msgId = msgMap.get("MsgId");
        log.info("voice");
        return recognition;
    }

    /**
     * 处理视频
     */
    public static String handleVideo(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        log.info("video");
        return "";
    }

    /**
     * 处理短视频
     */
    public static String handleShortvideo(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        log.info("shortvideo");
        return "";
    }

    /**
     * 处理链接
     */
    public static String handleLink(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String title = msgMap.get("Title");
        String Description = msgMap.get("Description");
        String url = msgMap.get("Url");
        String msgId = msgMap.get("MsgId");
        log.info("link");
        return "";
    }

    /**
     * 处理定位
     */
    public static String handleLocation(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        log.info("location");
        return "";
    }

    /**
     * 处理关注
     */
    public static String handleSubscribe(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        return "感谢你的关注[Facepalm]";
    }

    /**
     * 处理取关
     */
    public static String handleUnsubscribe(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        log.info("unsubscribe");
        return "";
    }

    /**
     * 处理点击获取消息
     */
    public static String handleClick(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String eventKey = msgMap.get("EventKey"); //事件KEY值，与自定义菜单接口中KEY值对应
        log.info("click");
        return "";
    }

    /**
     * 处理点击菜单跳转
     */
    public static String handleView(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String eventKey = msgMap.get("EventKey"); //事件KEY值，设置的跳转URL
        log.info("view");
        return "";
    }

    /**
     * 处理扫描带参数二维码
     */
    public static String handleScan(Map<String, String> msgMap) {
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String CreateTime = msgMap.get("CreateTime");
        String msgType = msgMap.get("MsgType");
        String content = msgMap.get("Content");
        String msgId = msgMap.get("MsgId");
        log.info("scan");
        return "";
    }


}
