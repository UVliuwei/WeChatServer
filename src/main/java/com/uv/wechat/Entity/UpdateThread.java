package com.uv.wechat.Entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uv.wechat.Utils.AccessTokenUtil;
import java.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * @author liuwei
 * @date 2018/9/8 14:40
 * 获取token'线程
 */
@Slf4j
public class UpdateThread extends TimerTask {

    public static final String APPID = "wx19be0119fb835c35";
    public static final String SECRET = "049a8b5fa33792ce702a995896ebbd5a";
    public static final String GRANT_TYPE = "client_credential";
    public static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + GRANT_TYPE + "&appid=" + APPID + "&secret=" + SECRET;

    private RestTemplate restTemplate = new RestTemplate();
    //重试次数
    private static int retryNum = 1;

    @Override
    public void run() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        if ("200".equals(responseEntity.getStatusCode().toString())) {
            String body = responseEntity.getBody();
            JSONObject object = JSON.parseObject(body);
            String token = object.getString("access_token");
            if (StringUtils.isNotBlank(token)) {
                AccessTokenUtil.token = token;
                //更新成功，重置次数
                retryNum = 0;
                log.info("token更新成功,token=" + token);
            }
        } else {
            try {
                do {
                    //5分钟后重试
                    log.info("token更新失败，正在进行第" + retryNum + "次重试！");
                    Thread.sleep(1000 * 5);
                    retryNum++;
                    this.run();
                } while (retryNum <= 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("token线程出错");
            }
        }
    }
}
