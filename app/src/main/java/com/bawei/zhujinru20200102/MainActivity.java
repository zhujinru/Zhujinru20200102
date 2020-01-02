package com.bawei.zhujinru20200102;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作:a zhujinru
 * 时间:2020/1/2 9:23
 * 作用:主页面
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.a_image)
    ImageView aImage;
    @BindView(R.id.recy)
    RecyclerView recy;
//获取数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NetUtil.getInstance().getJsonGet("http://172.17.8.100/small/commodity/v1/bannerShow", new NetUtil.MyCallBack() {
            @Override
            public void onGetJson(String json) {
                Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                List<GsonBean.ResultBean> result = gsonBean.getResult();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recy.setLayoutManager(linearLayoutManager);
                recy.setAdapter(new MyAdapter(result));
                NetUtil.getInstance().getPho("http://172.17.8.100/images/small/banner/cj.png",aImage);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("xx","错误");
            }
        });
    }
}
