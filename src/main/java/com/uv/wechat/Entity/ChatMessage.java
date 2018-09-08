package com.uv.wechat.Entity;

import lombok.Data;

/*
 * @author liuwei
 * @date 2018/9/8 12:09
 * 微信消息
 */
@Data
public class ChatMessage {

    //目的用户
    private String ToUserName;
    //来源用户
    private String FromUserName;
    //发送时间
    private String CreateTime;
    //消息类型
    private String MsgType;
    //消息内容
    private String Content;
    //消息ID
    private String MsgId;

}
