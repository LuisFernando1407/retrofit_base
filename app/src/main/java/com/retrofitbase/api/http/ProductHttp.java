package com.retrofitbase.api.http;

import android.content.Context;

import com.retrofitbase.api.BaseCallbackApi;
import com.retrofitbase.api.request.ApiRequest;
import com.retrofitbase.api.response.ProductsResponse;
import com.retrofitbase.util.Util;

import retrofit2.Call;
import retrofit2.Response;

public class ProductHttp {

    private ApiRequest request;
    private Context context;

    public ProductHttp(Context context){
        this.context = context;

        request = new ApiRequest();
    }

    public void index(){
        Call<ProductsResponse> callProducts = request.getServices().products();

        callProducts.enqueue(new BaseCallbackApi<ProductsResponse>(context){
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                super.onResponse(call, response);

                if(response.isSuccessful()){
                    Util.alert(context, "Somente o primeiro produto",
                            response.body() != null ? response.body().getProductList().get(0).toString() : null
                    );
                }
            }
        });
    }

    public void show(int id){}

    public void update(int id){}

    public void delete(int id){}
}