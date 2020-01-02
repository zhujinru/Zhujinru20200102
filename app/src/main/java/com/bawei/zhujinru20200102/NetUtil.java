package com.bawei.zhujinru20200102;

import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作:a zhujinru
 * 时间:2020/1/2 9:23
 * 作用:工具类
 */
public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;


    private NetUtil() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }

    //联网请求
    public void getJsonGet(String httpUrl,MyCallBack myCallBack){
        Request request = new Request.Builder().get().url(httpUrl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null&&response.isSuccessful()) {
                    String string = response.body().string();
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             myCallBack.onGetJson(string);
                         }
                     });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("错误"));
                        }
                    });
                }
            }
        });
    }
    //获取图片
    public void getPho(String phoUrl, ImageView imageView){
        Glide.with(imageView).load(phoUrl).into(imageView);
    }
    //接口
    public interface MyCallBack{
        void onGetJson(String json);
        void onError(Throwable throwable);
    }
}
