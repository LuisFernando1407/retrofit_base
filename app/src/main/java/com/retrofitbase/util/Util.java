package com.retrofitbase.util;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.view.Window;

import com.retrofitbase.R;
import com.retrofitbase.RetrofitBaseApplication;

import java.util.Objects;

public class Util {
    public static void setApiRefreshToken(String refreshToken) {
        SharedPreferences mPreferences = getSessionPreferences();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("API_REFRESH_TOKEN", refreshToken);
        editor.apply();
    }

    public static String getApiRefreshToken() {
        return getSessionPreferences().getString("API_REFRESH_TOKEN", null);
    }

    public static SharedPreferences getSessionPreferences() {
        Context ctx = RetrofitBaseApplication.getInstance();
        return ctx.getSharedPreferences("SESSION_PREFERENCES", ctx.MODE_PRIVATE);
    }

    public static String getApiToken() {
        return getSessionPreferences().getString("API_TOKEN", null);
    }

    public static void setApiToken(String token) {
        SharedPreferences mPreferences = getSessionPreferences();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("API_TOKEN", token);
        editor.apply();
    }

    public static Dialog loadingDialog(final Context ctx) {
        Dialog loading = new Dialog(ctx);
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading.setContentView(R.layout.dialog_loading);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(loading.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        loading.setCanceledOnTouchOutside(false);
        loading.setCancelable(false);
        return loading;
    }

    public static void alert(Context ctx, String title, String message) {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ctx);
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(Html.fromHtml(message));
        alertDialog.show();
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                RetrofitBaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}