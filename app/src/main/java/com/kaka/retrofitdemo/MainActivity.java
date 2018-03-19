package com.kaka.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kaka.retrofitdemo.net.ApiService;
import com.kaka.retrofitdemo.net.RetrofitManager;
import com.kaka.retrofitdemo.net.bean.ResponseData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitManager.create(ApiService.class).getHomeBanners()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseData>() {
                            @Override
                            public void accept(@NonNull ResponseData responseData) throws Exception {
                                Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });



    }
}
