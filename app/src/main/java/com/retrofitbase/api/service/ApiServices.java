package com.retrofitbase.api.service;

import com.retrofitbase.Constants;
import com.retrofitbase.api.response.LoginResponse;
import com.retrofitbase.api.response.ProductsResponse;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {

    @Headers(Constants.CONTENT_TYPE)
    @POST(Constants.LOGIN)
    Call<LoginResponse> login(@Body RequestBody body);

    @Headers(Constants.CONTENT_TYPE)
    @GET(Constants.PRODUCTS)
    Call<ProductsResponse> products();
}