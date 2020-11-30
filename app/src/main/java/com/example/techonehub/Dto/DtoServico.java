package com.example.techonehub.Dto;

public class DtoServico {
    private int Id;
    private String Cpf, Nm, Cnpj, Tel, DtInicio, DtPrazo, Servico, Sistema, Desc, Email, Ende, Espc;
    private double Valor;

    public DtoServico(int id, String cpf, String nm, String cnpj, String tel, String dtInicio, String dtPrazo, String servico, String sistema, String desc, String email, String ende, String espc, double valor) {
        Id = id;
        Cpf = cpf;
        Nm = nm;
        Cnpj = cnpj;
        Tel = tel;
        DtInicio = dtInicio;
        DtPrazo = dtPrazo;
        Servico = servico;
        Sistema = sistema;
        Desc = desc;
        Email = email;
        Ende = ende;
        Espc = espc;
        Valor = valor;
    }

    public DtoServico(){}

    public String getEspc() {
        return Espc;
    }

    public void setEspc(String espc) {
        Espc = espc;
    }

    public String getEnde() {
        return Ende;
    }

    public void setEnde(String ende) {
        Ende = ende;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getNm() {
        return Nm;
    }

    public void setNm(String nm) {
        Nm = nm;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getDtInicio() {
        return DtInicio;
    }

    public void setDtInicio(String dtInicio) {
        DtInicio = dtInicio;
    }

    public String getDtPrazo() {
        return DtPrazo;
    }

    public void setDtPrazo(String dtPrazo) {
        DtPrazo = dtPrazo;
    }

    public String getServico() {
        return Servico;
    }

    public void setServico(String servico) {
        Servico = servico;
    }

    public String getSistema() {
        return Sistema;
    }

    public void setSistema(String sistema) {
        Sistema = sistema;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }
}
