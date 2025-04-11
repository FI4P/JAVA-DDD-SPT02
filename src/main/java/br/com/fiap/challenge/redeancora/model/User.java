package br.com.fiap.challenge.redeancora.model;

import org.jetbrains.annotations.NotNull;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean emailValidation(@NotNull String email){
        return email.contains("@");
    }

    public boolean authenticate(@NotNull String password) {
        return password.equals(this.password);
    }

}
