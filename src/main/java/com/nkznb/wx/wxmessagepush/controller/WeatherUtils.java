package com.nkznb.wx.wxmessagepush.controller;

/**
 * @author Can.Ru
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cVzhanshi
 * @create 2022-08-04 22:02
 */
public class WeatherUtils {
    public static void main(String[] args) {
        System.out.println(getWeather());
    }
    public static Weather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<String,String>();
        map.put("district_id","310112"); // 地方行政代码
        map.put("data_type","all");//这个是数据类型
        map.put("ak","opPRf3UKzAuoQjxqGeRTiKPLBxgZ7AGu");
        String res = restTemplate.getForObject(
                "https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}",
                String.class,
                map);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text")); //阴
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class")); //6级
        weather.setWind_dir(now.getString("wind_dir")); //东北风

        JSONObject location = json.getJSONObject("result").getJSONObject("location");
        weather.setCity(location.getString("city"));
        weather.setName(location.getString("name"));
        return weather;
    }
}

