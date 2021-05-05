package com.example.techonehub.model.Dto;

public class DtoCliente {
    private int  Id;
    private String Nm, Cpf, Tel, Ende, Rg, Email, Dt;

    public DtoCliente(int id, String nm, String cpf, String tel, String ende, String rg, String email, String dt) {
        Id = id;
        Nm = nm;
        Cpf = cpf;
        Tel = tel;
        Ende = ende;
        Rg = rg;
        Email = email;
        Dt = dt;
    }
    public DtoCliente(){}

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

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEnde() {
        return Ende;
    }

    public void setEnde(String ende) {
        Ende = ende;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String rg) {
        Rg = rg;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDt() {
        return Dt;
    }

    public void setDt(String dt) {
        Dt = dt;
    }
}
