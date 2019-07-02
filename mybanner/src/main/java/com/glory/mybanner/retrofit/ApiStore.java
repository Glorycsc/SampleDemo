package com.glory.mybanner.retrofit;

import com.glory.mybanner.BannerModel;
import com.glory.mybanner.UploadThumbModelOkHttp;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Create by glorizz on 2018/7/17
 * Describe:
 */
public interface ApiStore {
     String API_SERVER_URL = "http://124.205.9.251:8082/";//北京

    //轮播图
    @POST("/mobile/index/noticeList")
    Call<BannerModel> bannerData(@Query("notice_type") String notice_type);



    //上传文件
    @Multipart
    @POST("/mobile/fileUpload/plupload")
//    Observable<UploadThumbModel> plupload(@Part("file") MultipartBody file, @Query("module") String module);
    Call<UploadThumbModelOkHttp> plupload(@Part MultipartBody.Part file, @Query("module") String module);
}
