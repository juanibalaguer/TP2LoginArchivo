package com.example.tp2loginarchivo.request;

import android.content.Context;
import android.widget.Toast;

import com.example.tp2loginarchivo.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static File archivo;
    public static File crearArchivo(Context context) {
        if(archivo == null) {
            archivo = new File(context.getFilesDir(), "/usuario.dat");
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error al crear el archivo", Toast.LENGTH_LONG).show();
            }
        }
        return archivo;
    }

    public void guardarUsuario(Context context, Usuario usuario) {
        crearArchivo(context);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(usuario);
            bufferedOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public Usuario leerUsuario(Context context) {
        Usuario usuario = new Usuario();
        try {
            crearArchivo(context);
            FileInputStream fileInputStream  = new FileInputStream(archivo);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            usuario = (Usuario) objectInputStream.readObject();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al leer los datos", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean logIn(Context context, String email, String contraseña) {
        crearArchivo(context);
        Usuario usuario = leerUsuario(context);
        String emailValido = usuario.getEmail();
        String contraseñaValida = usuario.getContraseña();
        if (email.equals(emailValido) && contraseña.equals(contraseñaValida)) {
            return true;
        } else
            return false;
    }
}
