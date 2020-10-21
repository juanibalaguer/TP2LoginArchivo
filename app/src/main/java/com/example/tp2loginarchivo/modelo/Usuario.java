package com.example.tp2loginarchivo.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

        private String dni;
        private String nombre;
        private String apellido;
        private String email;
        private String contraseña;

        public Usuario(String dni, String nombre, String apellido, String email, String contraseña) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.contraseña = contraseña;
        }
        public Usuario() {}

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        public String toString() {
            return "Usuario {" +
                    "DNI = "+ this.dni
                    + "Nombre = " + this.nombre
                    + "Apellido = " + this.apellido
                    + "Email = " + this.email
                    + "Contraseña = " + this.contraseña
                    +"}";
        }
    }

