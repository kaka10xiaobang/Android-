package com.kaka.retrofitdemo.net;

import com.kaka.retrofitdemo.net.bean.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by kaka on 2018/3/14.
 * email:375120706@qq.com
 */

public interface ApiService {

    @GET("/banner/json")
    Observable<ResponseData> getHomeBanners();
}
