package com.nkznb.wx.wxmessagepush.controller;



import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;

/**
 * @author Can.Ru
 */

public class Pusher {

    private static String appId = "wx8e914de0a1e6b014";
    private static String secret = "103040575c53667883388d941cb43c8c";



    public static void push(String openId,String templateId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .build();

        try {
            //3,如果是正式版发送模版消息，这里需要配置你的信息
            Weather weather = WeatherUtils.getWeather();
            Map<String, String> map = CaiHongPiUtils.getEnsentence();
            templateMessage.addData(new WxMpTemplateData("riqi",weather.getDate() + "  "+ weather.getWeek(),"#00BFFF"));
            templateMessage.addData(new WxMpTemplateData("tianqi",weather.getText_now()+ " ","#00FFFF"));
            templateMessage.addData(new WxMpTemplateData("low",weather.getLow() + "","#173177"));
            templateMessage.addData(new WxMpTemplateData("temp",weather.getTemp() + "","#EE212D"));
            templateMessage.addData(new WxMpTemplateData("high",weather.getHigh()+ "","#FF6347" ));
            templateMessage.addData(new WxMpTemplateData("windclass",weather.getWind_class()+ "","#42B857" ));
            templateMessage.addData(new WxMpTemplateData("winddir",weather.getWind_dir()+ " ","#B95EA3" ));
            templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPiUtils.getCaiHongPi(),"#FF69B4"));
            templateMessage.addData(new WxMpTemplateData("lianai",JiNianRiUtils.getLianAi()+"","#FF1493"));
            templateMessage.addData(new WxMpTemplateData("shengri",JiNianRiUtils.getBirthday_Zhang()+"","#FFA500"));
            templateMessage.addData(new WxMpTemplateData("shengri2",JiNianRiUtils.getBirthday_Ru()+"","#FFA500"));
            templateMessage.addData(new WxMpTemplateData("en",map.get("en") +"","#C71585"));
            templateMessage.addData(new WxMpTemplateData("zh",map.get("zh") +"","#C71585"));
            String beizhu = "❤";
            if(JiNianRiUtils.getLianAi() % 365 == 0){
                beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
            }
            if(JiNianRiUtils.getBirthday_Zhang()  == 0){
                beizhu = "Jade Zhang今天是生日，生日快乐呀！";
            }
            if(JiNianRiUtils.getBirthday_Ru()  == 0){
                beizhu = "Can Ru今天是生日，生日快乐呀！";
            }
            templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}

