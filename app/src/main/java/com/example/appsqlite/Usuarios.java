package com.example.appsqlite;


public class Usuarios {
    private String nome;
    private String email;
    private int codigo;

    public Usuarios() {
    }

    public Usuarios(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Usuarios(String nome, String email, int codigo) {
        this.nome = nome;
        this.email = email;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}



