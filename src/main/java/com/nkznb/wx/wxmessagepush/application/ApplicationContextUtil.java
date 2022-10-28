package com.nkznb.wx.wxmessagepush.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Can.Ru
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    public static HashMap<String, String> methodMap;
    ApplicationContextUtil(){
        methodMap = new HashMap<>();
        methodMap.put("easyRule3","easyRule3(a,b)");
        methodMap.put("easyRule1","easyRule1()");
    }

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> name) throws BeansException{
        return context.getBean(name);
    }
}
