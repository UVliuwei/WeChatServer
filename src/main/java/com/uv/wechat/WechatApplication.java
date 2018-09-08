package com.uv.wechat;

import com.uv.wechat.Utils.AccessTokenUtil;
import com.uv.wechat.Utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@Slf4j
public class WechatApplication {

	public static void main(String[] args) throws InterruptedException {

        AccessTokenUtil.updateToken();
		SpringApplication.run(WechatApplication.class, args);
		log.info("微信后台系统启动成功！");
	}
}
