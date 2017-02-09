package com.example.zc.myweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络连接工具类
 * Created by ZC on 2017/2/9.
 */

public class HttpUtil {

    /**
     * 使用okhttp发送http请求
     * @param address
     * @param callback
     */
    public static void sendOkhttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
