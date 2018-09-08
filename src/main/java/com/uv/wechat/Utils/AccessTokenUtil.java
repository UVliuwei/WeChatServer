package com.uv.wechat.Utils;
/*
 * @author liuwei
 * @date 2018/9/8 14:34
 * 获取TOKEN并更新
 */

import com.uv.wechat.Entity.UpdateThread;
import java.util.Timer;

public class AccessTokenUtil {

    public static String token = null;
    //1.5小时更新一次token
    private static final long UPDATE_TIME = 5400000;

    /**
     * 更新token
     */
    public static void updateToken() {

        new Timer().scheduleAtFixedRate(new UpdateThread(), 100, UPDATE_TIME);
    }
}
