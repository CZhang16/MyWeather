package com.example.zc.myweather.util;

import android.text.TextUtils;

import com.example.zc.myweather.db.City;
import com.example.zc.myweather.db.County;
import com.example.zc.myweather.db.Province;
import com.example.zc.myweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析json格式数据并将数据存入数据库
 * Created by ZC on 2017/2/9.
 */

public class Utility {

    /**
     * 将返回的JSON数据解析成Weather实体类
     * @param response
     * @return
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析和处理服务器返回的省级数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        //使用TextUtils判断返回数据是否为空或者空字符串
        if (!TextUtils.isEmpty(response)) {
            try {
                //使用response获取JSON数组
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    //获取JSON对象
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    //构建实体类
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    //将实体类保存至数据库
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        //使用TextUtils判断返回数据是否为空或者空字符串
        if (!TextUtils.isEmpty(response)) {
            try {
                //使用response获取JSON数组
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    //获取JSON对象
                    JSONObject cityObject = allCities.getJSONObject(i);
                    //构建实体类
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    //将实体类保存至数据库
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        //使用TextUtils判断返回数据是否为空或者空字符串
        if (!TextUtils.isEmpty(response)) {
            try {
                //使用response获取JSON数组
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    //获取JSON对象
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    //构建实体类
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    //将实体类保存至数据库
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
