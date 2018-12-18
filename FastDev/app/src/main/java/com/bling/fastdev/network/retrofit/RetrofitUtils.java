package com.bling.fastdev.network.retrofit;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 创建 Retrofit 单例模式
 */
public class RetrofitUtils {

    private static final String BASE_URL = "";
    private static final int CONNECT_TIME_OUT = 15;//连接超时时长n秒
    private static Retrofit retrofit;
    private static ApiService apiService;
    private volatile static RetrofitUtils instance;
    private static Context context;

    private RetrofitUtils() {
        retrofit = getRetrofit();
        apiService = getAPIService();
    }

    public static RetrofitUtils init(Context ct) {
        context=ct.getApplicationContext();
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                instance = new RetrofitUtils();
            }
        }
        return instance;

    }

    /**
     * retrofit设置okHttp
     * 添加日志拦截器方便 打印返回数据日志
     * 其他请求配置在此添加即可
     */
    private OkHttpClient okHttpClient() {
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new RequestInterceptor())
                .build();
        return client;
    }

    /**
     * 获取Retrofit 实例
     */

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

    /**
     * 获取 ApiService
     * created by dongliang
     * 2018/4/9  15:21
     */

    public static ApiService getAPIService() {

        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    /**
     * 用于下载文件
     * created by dongliang
     * 2018/4/9  17:55
     */
    public <T> ApiService getDownLoadService(final RetrofitCallback<T> callback) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                okhttp3.Response response = chain.proceed(chain.request());
                //将ResponseBody转换成我们需要的FileResponseBody
                return response.newBuilder().body(new FileResponseBody<T>(response.body(), callback)).build();
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        return service;
    }

}