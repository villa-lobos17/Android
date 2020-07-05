package com.wolve.uberclone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wolve.uberclone.R;
import com.wolve.uberclone.includes.MyToolbar;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    Toolbar mToolBar;
    TextInputEditText TUser;
    TextInputEditText TPassword;
    Button BLogin;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TUser = findViewById(R.id.txtUser);
        TPassword = findViewById(R.id.txtPassword);
        BLogin = findViewById(R.id.btnLogAction);
        MyToolbar.Show(this,"Login Usuario",true);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        alertDialog = new  SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento porfavor").build();

        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });


    }

    private void onLogin() {
        String email = TUser.getText().toString();
        String pass = TPassword.getText().toString();
        if(!email.isEmpty()&& !pass.isEmpty()&& pass.length()>6){
            alertDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "el login se realizo correctamente ",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña no validos ",Toast.LENGTH_SHORT).show();
                    }
                    alertDialog.hide();
                }
            });
        }
    }
}