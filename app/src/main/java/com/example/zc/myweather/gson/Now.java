package com.example.zc.myweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZC on 2017/2/9.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;
    }
}
