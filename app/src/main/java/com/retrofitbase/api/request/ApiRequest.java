package com.retrofitbase.api.request;

import com.google.gson.JsonObject;
import com.retrofitbase.api.auth.Authenticated;
import com.retrofitbase.api.service.ApiServices;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import timber.log.Timber;

public class ApiRequest extends Authenticated {

    private final MediaType JSON = MediaType.parse("application/json");

    public ApiRequest(){
        setupRetrofit();
    }

    public ApiServices getServices(){
        return retrofit.create(ApiServices.class);
    }

    /* Request Body */
    public RequestBody setBody(JsonObject object){
        return RequestBody.create(JSON, object.toString());
    }

    /* With show body */
    public RequestBody setBody(JsonObject object, boolean isShow){
        if(isShow)showBody(object);
        return RequestBody.create(JSON, object.toString());
    }

    /* Show body */
    private void showBody(JsonObject object){
        Timber.tag("RequestBody-d").d(object.toString());
    }

}