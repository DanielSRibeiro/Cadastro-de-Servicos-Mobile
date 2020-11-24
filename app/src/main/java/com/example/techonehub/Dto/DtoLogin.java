package com.example.techonehub.Dto;

public class DtoLogin {
    private String Login, Senha;

    public DtoLogin(String login, String senha) {
        Login = login;
        Senha = senha;
    }

    public DtoLogin(){}

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
