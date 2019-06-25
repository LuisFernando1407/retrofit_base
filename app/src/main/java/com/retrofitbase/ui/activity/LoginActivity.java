package com.retrofitbase.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.Toast;

import com.retrofitbase.R;
import com.retrofitbase.api.http.ProductHttp;
import com.retrofitbase.api.http.AuthHttp;
import com.retrofitbase.util.Util;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;

    private AppCompatButton enter;

    private AppCompatButton all_products;

    private AuthHttp authHttp;

    private ProductHttp productHttp;
    private AppCompatButton exit;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        enter = findViewById(R.id.enter);

        all_products = findViewById(R.id.all_products);

        exit = findViewById(R.id.exit);

        context = this;

        authHttp = new AuthHttp(context);

        enter.setOnClickListener(v -> authHttp.login(email.getText().toString(), pass.getText().toString()));

        exit.setOnClickListener(v -> {
            /* TODO: Logout */
            Util.setApiToken(null);
        });

        all_products.setOnClickListener(v -> {
            productHttp = new ProductHttp(context);

            if(Util.getApiToken() != null){
                productHttp.index();
            }else{
                Toast.makeText(context, "Usuário não logado", Toast.LENGTH_SHORT).show();
            }
        });

    }
}