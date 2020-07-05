package com.wolve.uberclone.activities.driver;

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
import com.wolve.uberclone.activities.client.RegisterActivity;
import com.wolve.uberclone.includes.MyToolbar;
import com.wolve.uberclone.models.Client;
import com.wolve.uberclone.models.Driver;
import com.wolve.uberclone.providers.AuthProvider;
import com.wolve.uberclone.providers.ClientProvider;
import com.wolve.uberclone.providers.DriverProvider;

public class RegisterDriverActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    AuthProvider authProvider;
    ClientProvider clientProvider;
    DriverProvider driverProvider;
    TextInputEditText user_Email;
    TextInputEditText user_pass;
    TextInputEditText user_name;
    TextInputEditText user_plate;
    TextInputEditText user_brand;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        MyToolbar.Show(this, "Registrar Conductor", true);

        authProvider = new AuthProvider();
        driverProvider = new DriverProvider();

        user_Email = findViewById(R.id.txtUserEmail);
        user_pass = findViewById(R.id.txtPasswordName);
        user_name = findViewById(R.id.txtUserName);
        user_plate = findViewById(R.id.txtVehiclePlate);
        user_brand = findViewById(R.id.txtVehicleBrand);

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
            String vehiclePlate = user_plate.getText().toString();
            String vehicleBrand = user_brand.getText().toString();

            SharedPreferences type = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
            sharedPreferences = getApplicationContext().getSharedPreferences("userType", MODE_PRIVATE);

            if (!email.isEmpty() && !name.isEmpty() && !pass.isEmpty()) {
                if (pass.length() > 6) {
                    register(name, email, pass, vehiclePlate,vehicleBrand);
                } else {
                    Toast.makeText(RegisterDriverActivity.this, "Señor ususario la contraseña debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterDriverActivity.this, "Señor ususario ingrese todos los campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void register(final String name, final String email, final String password, final String plate , final String brand) {
        authProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Driver client = new Driver(id, name, email,brand,plate);
                    create(client);
                } else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void create(Driver driver) {
        driverProvider.create(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterDriverActivity.this, "cliente creado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}