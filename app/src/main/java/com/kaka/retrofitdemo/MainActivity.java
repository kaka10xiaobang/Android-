package com.kaka.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kaka.retrofitdemo.net.bean.Advertisement;
import com.kaka.retrofitdemo.net.ApiService;
import com.kaka.retrofitdemo.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Observable<Advertisement> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call=RetrofitManager.create(ApiService.class).getHomeBanners();
                        call.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Advertisement>() {
                            @Override
                            public void accept(@NonNull Advertisement advertisement) throws Exception {
                                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
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
