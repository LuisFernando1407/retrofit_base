package com.retrofitbase.api.http;

import android.content.Context;

import com.google.gson.JsonObject;
import com.retrofitbase.api.BaseCallbackApi;
import com.retrofitbase.api.request.ApiRequest;
import com.retrofitbase.api.response.LoginResponse;
import com.retrofitbase.util.Util;
import retrofit2.Call;
import retrofit2.Response;


public class AuthHttp {

    private ApiRequest request;
    private Context context;

    public AuthHttp(Context context){
        this.context = context;

        request = new ApiRequest();
    }

    public void login(String cpf, String pass){

        JsonObject auth = new JsonObject();
        auth.addProperty("username", cpf);
        auth.addProperty("password", pass);

        Call<LoginResponse> callLogin = request.getServices().login(request.setBody(auth));

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