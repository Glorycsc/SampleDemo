package com.glory.mybanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.glory.mybanner.retrofit.ApiClient;
import com.glory.mybanner.retrofit.ApiStore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private BannerLayout bannerLayout;
    //    private static final String url1 = "http://124.205.9.251:8082/static//image/201807/04b973cf-eaeb-41cf-bd49-29c044ee3257.png";
    private static final String url2 = "http://124.205.9.251:8082/static//image/201807/28124b76-a99b-42dc-8fa0-3fea8f98e367.png";
    private static final String url3 = "http://124.205.9.251:8082/static//image/201807/e8816f2c-c7b4-4ae7-a4f3-7c0332c896c3.jpg";
    private List<Object> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerLayout = findViewById(R.id.bannerLayout);
//        stringList.add(url1);
//        stringList.add(url2);
//        stringList.add(url3);
//        bannerLayout.setOnBannerClickListener(new BannerLayout.OnBannerClickListener() {
//            @Override
//            public void onBannerClick(View itemView, int position) {
//                //直接写要做的事件即可
//                Toast.makeText(MainActivity.this, "bannerClick" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        bannerLayout.start(stringList);
        getBannerData();
//        findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("glz", "one start");
//                getBannerData();
//                Log.e("glz", "one finished");
//            }
//        });
//        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("glz", "two start");
//                bannerLayout.start(stringList);
//                Log.e("glz", "two finished");
//            }
//        });
    }

    private void getBannerData() {
        Call<BannerModel> call = ApiClient.getRetorfit().create(ApiStore.class).bannerData(BannerModel.BRAND);
        call.enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
//                for (BannerModel.DataBean bean : bannerModel.getData()) {
//                    stringList.add(bean.getImg_url());
//                    bannerLayout.start(stringList);
//                }
                stringList.add(bannerModel.getData().get(0));
                bannerLayout.start(stringList);
//                bannerLayout.start(stringList);

            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
