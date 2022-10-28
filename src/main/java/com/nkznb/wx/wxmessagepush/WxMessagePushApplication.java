package com.nkznb.wx.wxmessagepush;

import com.nkznb.wx.wxmessagepush.controller.Pusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@EnableScheduling
@SpringBootApplication
public class WxMessagePushApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxMessagePushApplication.class, args);
	}

	Logger log = Logger.getLogger("WechatController");
	//服务器时差8小时
	@Scheduled(cron = "0 0 0 * * ? ")
	public void goodMorning(){
		log.info("" + LocalDateTime.now());
		Pusher.push("oSXlU6q0zrPE8W97blkusXzvv0GM","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
		Pusher.push("oSXlU6qGFN3M7LKxwrH2MQsb6ByA","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
	}

	//服务器时差8小时
	@Scheduled(cron = "0 30 9 * * ? ")
	public void goodMorning1(){
		log.info("" + LocalDateTime.now());
		Pusher.push("oSXlU6q0zrPE8W97blkusXzvv0GM","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
		Pusher.push("oSXlU6qGFN3M7LKxwrH2MQsb6ByA","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
	}

}
