package com.retrofitbase.api.http;

import android.content.Context;
import com.retrofitbase.Constants;
import com.retrofitbase.util.Util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticatedHttp {

    protected Retrofit retrofit;
    protected Context context;

    private static final int CONNECTION_TIMEOUT = 20*1000;

    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {

        Request newRequest = chain.request();

        if(Util.getApiToken() != null){
            newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + Util.getApiToken())
                    .build();
        }

        return chain.proceed(newRequest);
    }).connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).build();

    protected void setupClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}