package com.wolve.uberclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wolve.uberclone.R;

public class MainActivity extends AppCompatActivity {

    Button mButtonImUser;
    Button mRegisterUser;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonImUser = findViewById(R.id.btn_imdriver);
        mRegisterUser = findViewById(R.id.btn_register);
        sharedPreferences = getApplicationContext().getSharedPreferences("userType", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        mButtonImUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "driver");
                editor.apply();
                goToSelectAuth();
            }
        });
        mRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "client");
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }

}