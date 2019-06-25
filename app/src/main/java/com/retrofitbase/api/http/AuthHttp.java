package com.retrofitbase.api.http;

import android.content.Context;

import com.google.gson.JsonObject;
import com.retrofitbase.api.BaseCallbackApi;
import com.retrofitbase.api.auth.Authenticated;
import com.retrofitbase.api.response.LoginResponse;
import com.retrofitbase.api.service.ApiServices;
import com.retrofitbase.util.Util;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;


public class AuthHttp extends Authenticated {
     /*
        Method: @AuthHttp
     */

    private final MediaType JSON = MediaType.parse("application/json");

    public AuthHttp(Context context){
        this.context = context;

        setupRetrofit();
    }

    public void login(String email, String pass){

        ApiServices services = retrofit.create(ApiServices.class);

        JsonObject auth = new JsonObject();

        auth.addProperty("email", email);
        auth.addProperty("password", pass);

        RequestBody body = RequestBody.create(JSON, auth.toString());

        Call<LoginResponse> callLogin = services.login(body);
        callLogin.enqueue(new BaseCallbackApi<LoginResponse>(context, true){
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                super.onResponse(call, response);

                if(response.isSuccessful()){

                    Util.setApiRefreshToken(response.body() != null ? response.body().getRefreshToken() : null);
                    Util.setApiToken(response.body() != null ? response.body().getToken() : null);

                    Util.alert(context, "Sucesso", "Bem vindo, " + (response.body() != null ? response.body().getUser().getName() : null));
                }
            }
        });
    }
}