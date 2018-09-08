package com.uv.wechat.controller;

import com.uv.wechat.Utils.CommonUtil;
import com.uv.wechat.Utils.HandleUtil;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/9/8 10:08
 *
 */
@RestController
@Slf4j
public class ChatController {

    private static final String WECHAT_TOKEN = "val1314";

    /**
     * 用于微信服务器检查
     */
    @GetMapping("wechat")
    public String checkToken(String signature, String nonce, String echostr) {
        log.info("微信验证服务器TOKEN: signature=" + signature + "  echostr=" + echostr);
        return echostr;
    }

    /**
     * 接受信息
     */
    @PostMapping("wechat")
    public String recieveMsg(HttpServletRequest request) {
        Map<String, String> msgMap = CommonUtil.xml2Map(request);
        String msgType = msgMap.get("MsgType");
        if(CommonUtil.MESSAGE_EVENT.equals(msgType)) {
            String event = msgMap.get("Event");
            if (CommonUtil.MESSAGE_SUBSCRIBE.equals(event)) {
                return HandleUtil.handleSubscribe(msgMap);
            }
            return "未处理的信息";
        }
        switch (msgType) {
            case CommonUtil.MESSAGE_TEXT:
                return HandleUtil.handleText(msgMap);
            case CommonUtil.MESSAGE_IMAGE:
                return HandleUtil.handleImage(msgMap);
            case CommonUtil.MESSAGE_VOICE:
                return HandleUtil.handleVoice(msgMap);
            case CommonUtil.MESSAGE_LINK:
                return HandleUtil.handleLink(msgMap);
            default:
                return "";
        }
    }
}
