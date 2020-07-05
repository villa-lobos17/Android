package com.wolve.uberclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wolve.uberclone.R;
import com.wolve.uberclone.activities.client.RegisterActivity;
import com.wolve.uberclone.activities.driver.RegisterDriverActivity;
import com.wolve.uberclone.includes.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        setContentView(R.layout.activity_select_option_auth);
        MyToolbar.Show(this,"Seleccionar opcion",true);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
        sharedPreferences = getApplicationContext().getSharedPreferences("userType", MODE_PRIVATE);

    }

    public void goToRegister() {
        sharedPreferences = getApplicationContext().getSharedPreferences("userType", MODE_PRIVATE);
        String typeUser = sharedPreferences.getString("user", "");
        if (typeUser.equals("client")) {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterDriverActivity.class);
            startActivity(intent);
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}