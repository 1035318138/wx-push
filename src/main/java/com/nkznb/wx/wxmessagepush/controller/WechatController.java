package com.nkznb.wx.wxmessagepush.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * @author Can.Ru
 */
@Controller
@RequestMapping("/weixin")
public class WechatController {

    Logger log = Logger.getLogger("WechatController");

    private static String WECHAT_TOKEN = "1035318138"; //WECHAT_TOKEN和你申请时填写的Token一样

    @RequestMapping(value = "/verityToken")
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("in");

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            String log = "name =" + name + "     value =" + value;
        }

        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();
        out.print(echostr);
        out.close();
        log.info("out");
    }


    @RequestMapping(value = "/execute")
    public void execute(){
        System.out.println("execute in");
        Pusher.push("oSXlU6q0zrPE8W97blkusXzvv0GM","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
        Pusher.push("oSXlU6qGFN3M7LKxwrH2MQsb6ByA","uZHgEP_1b4w785iZoiN6isbyJpGZBFQ6bip_RSP5zzo");
    }


    @RequestMapping(value = "/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("1");
        out.close();

    }

    public Boolean easyRule1(){
        System.out.println("easyRule1");
        return true;
    }
    public Boolean easyRule2(){
        return false;
    }

    public Boolean easyRule3(Integer a,Integer b){
        System.out.println(a);
        System.out.println(b);

        return a>b;
    }


    public String doQueryBehaviorData(String valueBegin, String valueEnd, String demarcationType,String type,String jsonParam) throws InterruptedException {
        System.out.println(valueBegin);
        System.out.println(valueEnd);
        System.out.println(demarcationType);
        System.out.println(type);
        System.out.println(jsonParam);
        return "s";
    }





}
