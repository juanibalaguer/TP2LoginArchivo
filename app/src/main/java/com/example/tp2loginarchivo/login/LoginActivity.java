package com.example.tp2loginarchivo.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2loginarchivo.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etContraseña;
    private Button btLogin, btRegistrar;
    private LoginActivityViewModel loginActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    private void inicializar() {
        loginActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        etEmail = findViewById(R.id.etEmail);
        etContraseña = findViewById(R.id.etContraseña);
        btLogin = findViewById(R.id.btLogin);
        btRegistrar = findViewById(R.id.btRegistrar);
        loginActivityViewModel.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
        loginActivityViewModel.getContraseña().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etContraseña.setText(s);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityViewModel.iniciarSesion(etEmail.getText().toString(), etContraseña.getText().toString());
            }
        });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityViewModel.registrar();
            }
        });
    }
}