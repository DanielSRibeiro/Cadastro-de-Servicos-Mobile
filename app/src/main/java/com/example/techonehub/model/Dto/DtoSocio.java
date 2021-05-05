package com.example.techonehub.model.Dto;
public class DtoSocio {
    private int Id;
    private String Nm, Cpf, Espec;

    public DtoSocio(int id, String nm, String cpf, String espec) {
        Id = id;
        Nm = nm;
        Cpf = cpf;
        Espec = espec;
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
}
