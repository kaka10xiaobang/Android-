package com.kaka.retrofitdemo.net;

import android.util.Log;
import android.widget.Toolbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kaka on 2018/3/14.
 * email:375120706@qq.com
 */

public class RetrofitManager {
    private static  volatile Retrofit mInstance;
    private static OkHttpClient mOkHttpClient;
    private static final String TAG="OkHttp";
    private static Interceptor mInterceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            request=request.newBuilder().headers(buildHeaders()).build();
            logForRequest(request);
            Response response=chain.proceed(request);
            response=logForResponse(response);
            return response;
        }
    };

    private static Headers buildHeaders() {
        Map<String,String> map=new HashMap<>();
        map.put("time",System.currentTimeMillis()+"");
        Headers headers=Headers.of(map);
        return headers;
    }

    private static Response logForResponse(Response response) {
        Log.e(TAG,"response's log---------------------------start");
        Log.e(TAG,"protocol:"+response.protocol());
        Log.e(TAG,"code: "+response.code());
        Headers headers=response.headers();
        if (headers!=null&&headers.size()!=0){
            Log.e(TAG,"headers:"+response.headers().toString());
        }
        try {
            ResponseBody body=response.peekBody(1024*1024);
            if (body!=null){
                MediaType type=body.contentType();
                Log.e(TAG,"body:"+body.string());
            }
//            ResponseBody.create(type,body)
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"response's log---------------------------end");
        return  response;
    }


    /**
     * request log日子打印
     * @param request
     */
    private static void logForRequest(Request request) {
        Log.e(TAG,"request's log---------------------------start");
        Log.e(TAG,"url:"+request.url());
        Log.e(TAG,"method: "+request.method());
        Headers headers=request.headers();
        if (headers!=null&&headers.size()!=0){
            Log.e(TAG,"headers:"+request.headers().toString());
        }
        RequestBody body=request.body();
        if (body!=null){
            Log.e(TAG,"body:"+request.body().toString());
        }
        Log.e(TAG,"request's log---------------------------end");
    }


    private static OkHttpClient getOkHttpClient(){
        if (mOkHttpClient==null){
            synchronized (RetrofitManager.class){
                if (mOkHttpClient==null){
                    mOkHttpClient=new OkHttpClient.Builder()
                            .addInterceptor(mInterceptor)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(10,TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }


    public static<T> T create(Class<T> clazz){
        Retrofit retrofit=new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl("http://wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

}
