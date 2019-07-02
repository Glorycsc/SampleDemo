package com.glory.mybanner;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.glory.mybanner.retrofit.ApiClient;
import com.glory.mybanner.retrofit.ApiStore;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by glorizz on 2018/9/3
 * Describe:
 */
public class UploadFileActivity extends Activity {
    MultipartBody.Part body;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置一张要上传的图片
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "q.jpg");
        //创建要使用的这个的File
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/form-data"), file);
        body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        uploadFile(body);
        getPermission();
    }

    private void uploadFile(MultipartBody.Part body) {
        Call<UploadThumbModelOkHttp> call = ApiClient.getRetorfit().create(ApiStore.class).plupload(body, "user_headImg");
        call.enqueue(new Callback<UploadThumbModelOkHttp>() {
            @Override
            public void onResponse(Call<UploadThumbModelOkHttp> call, Response<UploadThumbModelOkHttp> response) {
                String s = response.body().toString();
                Toast.makeText(UploadFileActivity.this, s, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UploadThumbModelOkHttp> call, Throwable t) {
                Toast.makeText(UploadFileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------------请求权限-----------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermission() {
        switch (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            case PackageManager.PERMISSION_GRANTED: {//已经有了权限了
                Toast.makeText(this, "已经有权限了", Toast.LENGTH_SHORT).show();
                uploadFile(body);
                break;
            }
            case PackageManager.PERMISSION_DENIED: {//被拒绝授权
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 777);
                break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 777) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
                uploadFile(body);
            } else {
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //----------------------------------------请求权限-----------------------------------------------
}