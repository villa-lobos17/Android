package com.wolve.uberclone.activities.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wolve.uberclone.R;
import com.wolve.uberclone.includes.MyToolbar;
import com.wolve.uberclone.models.Client;
import com.wolve.uberclone.providers.AuthProvider;
import com.wolve.uberclone.providers.ClientProvider;
import com.wolve.uberclone.providers.DriverProvider;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    AuthProvider authProvider;
    ClientProvider clientProvider;
    DriverProvider driverProvider;
    TextInputEditText user_Email;
    TextInputEditText user_pass;
    TextInputEditText user_name;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyToolbar.Show(this, "Registrar Usuario", true);

        authProvider = new AuthProvider();
        driverProvider = new DriverProvider();
        clientProvider = new ClientProvider();

        user_Email = findViewById(R.id.txtUserEmail);
        user_pass = findViewById(R.id.txtPasswordName);
        user_name = findViewById(R.id.txtUserName);
        btnRegister = findViewById(R.id.btnRegisterUserm);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
    }

    public void RegisterUser() {
        try {
            String email = user_Email.getText().toString();
            String pass = user_pass.getText().toString();
            String name = user_name.getText().toString();
            SharedPreferences type = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
            sharedPreferences = getApplicationContext().getSharedPreferences("userType", MODE_PRIVATE);

            if (!email.isEmpty() && !name.isEmpty() && !pass.isEmpty()) {
                if (pass.length() > 6) {
                    register(name, email, pass);
                } else {
                    Toast.makeText(RegisterActivity.this, "Señor ususario la contraseña debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Señor ususario ingrese todos los campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void register(final String name, final String email, String password) {
        authProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id, name, email);
                    create(client);
                } else {
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void create(Client client) {
        clientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "cliente creado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

/*
    private void saveUser(String id,String name , String email) {
        sharedPreferences = getApplicationContext().getSharedPreferences("userType",MODE_PRIVATE);
        String selectedUser = sharedPreferences.getString("user","");
        User user = new User();
        user.setEmail(email);
        user.setUser(name);
        if (selectedUser.equals("driver")) {
            databaseReference.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (selectedUser.equals("client")) {
            databaseReference.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/

}