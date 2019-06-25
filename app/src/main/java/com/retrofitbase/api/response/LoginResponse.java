package com.retrofitbase.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.retrofitbase.model.User;

public class LoginResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("user")
    @Expose
    private User user;

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public User getUser() {
        return user;
    }
}
