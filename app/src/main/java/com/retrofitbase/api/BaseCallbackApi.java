package com.retrofitbase.api;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.retrofitbase.ui.activity.error.ServerErrorActivity;
import com.retrofitbase.util.Util;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallbackApi<T> implements Callback<T> {

    private Context context;
    private Dialog dialog;

    private boolean isLogin;

    protected BaseCallbackApi(Context context){
        this.context = context;

        /* Progress */
        dialog = Util.loadingDialog(context);
        dialog.show();
    }

    protected BaseCallbackApi(Context context, boolean isLogin){
        this.context = context;
        this.isLogin = isLogin;
        /* Progress */
        dialog = Util.loadingDialog(context);
        dialog.show();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(dialog.isShowing()){
            dialog.dismiss();
        }

        alertStatusCode(response.code());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(dialog.isShowing()){
            dialog.dismiss();
        }

        whenConnectTimeOut(t);
    }

    private void alertStatusCode(int statusCode) {
        if(statusCode == 500) {
            Util.alert(context, "Falha", "Um erro interno ocorreu no servidor, por favor tente mais tarde.");
        }else if(statusCode == 404) {
            Util.alert(context, "Falha", "Servidor indisponível, por favor tente mais tarde.");
        }else if(statusCode == 401){
            if(isLogin){
                Util.alert(context, "Falha", "Usuário ou senha incorreto");
            }
            /* Todo: Refresh Token */
        }
    }

    private void  whenConnectTimeOut(Throwable throwable) {
        if(throwable instanceof SocketTimeoutException
                || throwable instanceof ConnectTimeoutException) {
            Intent intent = new Intent(context, ServerErrorActivity.class);
            intent.putExtra("message", "Tempo para conexão esgostado ou servidor indisponível, por favor tente mais tarde.");
            context.startActivity(intent);
        }

        if(throwable instanceof ConnectException){
            Intent intent = new Intent(context, ServerErrorActivity.class);
            intent.putExtra("message", "Verifique seu acesso a internet ou tente novamente mais tarde.");
            context.startActivity(intent);
        }
    }
}