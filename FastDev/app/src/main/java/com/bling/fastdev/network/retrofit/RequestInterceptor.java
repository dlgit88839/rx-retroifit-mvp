package com.bling.fastdev.network.retrofit;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 构建Api公共请求参数
 * 实际应用中 各个接口有可能都需要 传入uuid 等
 *
 */

public class RequestInterceptor implements Interceptor {

    public static final String token= "token";

    /**
     * get请求拦截 添加参数
     * created by dongliang
     *  2018/10/31  13:27
     */
    private Request getIntercept(Request request){
        HttpUrl modifiedUrl = request.url().newBuilder()
//                .addQueryParameter(CLIENT, AppTools.getClient(context))
//                .addQueryParameter(VERSION, AppTools.getAppVersion(context))
//                .addQueryParameter(TOKEN, AppTools.getToken(context))
                .build();

        return request.newBuilder().url(modifiedUrl).build();

    }

    /**
     * post请求拦截 添加参数
     * created by dongliang
     *  2018/10/31  13:27
     */
    private Request postIntercept(Request request){

        if (request.body() instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();

            FormBody formBody = (FormBody) request.body();

            for (int i = 0; i < formBody.size(); i++) {
                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
            }
//            formBody = bodyBuilder.addEncoded(CLIENT, AppTools.getClient(context))
//                    .addEncoded(VERSION, AppTools.getAppVersion(context))
//                    .addEncoded(TOKEN, AppTools.getToken(context))
//                    .build();
            request = request.newBuilder().post(formBody).build();
        }
        return request;


    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        switch (request.method()) {

            case "GET":
              request= getIntercept(request);
                break;
            case "POST":
              request=postIntercept(request);
                break;
        }
        return chain.proceed(request);
    }
}
