package com.example.tp2loginarchivo.perfil;

import android.app.Application;
import android.content.Context;

import com.example.tp2loginarchivo.modelo.Usuario;
import com.example.tp2loginarchivo.request.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PerfilActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuario;
    private ApiClient apiClient;

    public PerfilActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }
    public LiveData<Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }
    public void guardarDatos(String dni, String nombre, String apellido, String email, String contraseña) {
        Usuario usuario = new Usuario(dni, nombre, apellido, email, contraseña);
        apiClient.guardarUsuario(context, usuario);
    }

    public void cargarDatos(Boolean login) {
        if(login) {
            Usuario usuario = apiClient.leerUsuario(context);
            this.usuario.setValue(usuario);
        }
    }
}