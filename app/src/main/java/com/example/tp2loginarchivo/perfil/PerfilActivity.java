package com.example.tp2loginarchivo.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp2loginarchivo.R;
import com.example.tp2loginarchivo.modelo.Usuario;

public class PerfilActivity extends AppCompatActivity {
    private EditText etDni, etNombre, etApellido, etEmail, etContraseña;
    private Button btGuardar;
    private Intent intent;
    private PerfilActivityViewModel perfilActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        intent = getIntent();
        inicializar();
    }

    private void inicializar() {
        etDni = findViewById(R.id.etDni);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEmail = findViewById(R.id.etEmail);
        etContraseña = findViewById(R.id.etContraseña);
        btGuardar = findViewById(R.id.btGuardar);
        perfilActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PerfilActivityViewModel.class);

        perfilActivityViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni());
                etNombre.setText(usuario.getNombre());
                etApellido.setText(usuario.getApellido());
                etEmail.setText(usuario.getEmail());
                etContraseña.setText(usuario.getContraseña());
            }
        });

        perfilActivityViewModel.cargarDatos(intent.getBooleanExtra("login", false));
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilActivityViewModel.guardarDatos(etDni.getText().toString(),
                        etNombre.getText().toString(),
                        etApellido.getText().toString(),
                        etEmail.getText().toString(),
                        etContraseña.getText().toString());
            }
        });
    }

}