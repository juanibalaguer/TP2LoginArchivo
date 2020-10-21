package com.example.tp2loginarchivo.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.tp2loginarchivo.perfil.PerfilActivity;
import com.example.tp2loginarchivo.request.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<String> mensaje;
    private MutableLiveData<String> contraseña;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }
    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }public LiveData<String> getContraseña() {
        if (contraseña == null) {
            contraseña = new MutableLiveData<>();
        }
        return contraseña;
    }
    public void iniciarSesion(String email, String contraseña) {
        if(apiClient.logIn(context, email, contraseña)) {
            Intent intent = new Intent(context, PerfilActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("login", true);
            context.startActivity(intent);
        } else {
            mensaje.setValue("Usuario y/o contraseña incorrecto/s");
            this.contraseña.setValue("");
        }
    }
    public void registrar() {
        Intent intent = new Intent(context, PerfilActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("login", false);
        context.startActivity(intent);

    }
}