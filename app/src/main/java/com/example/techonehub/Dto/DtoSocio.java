package com.example.techonehub.Dto;
public class DtoSocio {
    private int Id;
    private String Nm, Cpf, Espec, Login, Senha;

    public DtoSocio(int id, String nm, String cpf, String espec, String login, String senha) {
        Id = id;
        Nm = nm;
        Cpf = cpf;
        Espec = espec;
        Login = login;
        Senha = senha;
    }
    public DtoSocio(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNm() {
        return Nm;
    }

    public void setNm(String nm) {
        Nm = nm;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getEspec() {
        return Espec;
    }

    public void setEspec(String espec) {
        Espec = espec;
    }

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
