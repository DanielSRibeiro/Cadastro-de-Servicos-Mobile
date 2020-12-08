package com.example.techonehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.techonehub.Dto.DtoCliente;
import com.example.techonehub.Dto.DtoLogin;
import com.example.techonehub.Dto.DtoServico;
import com.example.techonehub.Dto.DtoSocio;

import java.util.ArrayList;

public class DaoTechOneHub extends SQLiteOpenHelper {

    final String CLIENTE = "TBL_CLIENTE";
    final String SOCIO = "TBL_SOCIO";
    final String LOGIN = "TBL_LOGIN";
    final String SERVICO = "TBL_SERVICO";
    final String CONTRATO = "TBL_CONTRATO";

    public DaoTechOneHub(@Nullable Context context) {
        super(context, "TECHONEHUB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cm1 = "CREATE TABLE " + LOGIN + " (" +
                "ID INTEGER PRIMARY KEY," +
                "LOGIN VARCHAR(80) NOT NULL," +
                "SENHA VARCHAR(11) NOT NULL)";

        String cm2 = "CREATE TABLE " + CLIENTE + " (" +
                "ID INTEGER PRIMARY KEY," +
                "NM VARCHAR(80) NOT NULL," +
                "CPF VARCHAR(12) UNIQUE NOT NULL," +
                "RG VARCHAR(12) NOT NULL," +
                "TEL VARCHAR(15) NOT NULL," +
                "ENDE VARCHAR(130) NOT NULL," +
                "EMAIL VARCHAR(80) NOT NULL," +
                "DT DATE NOT NULL)";

        String cm3 = "CREATE TABLE " + SOCIO + " (" +
                "ID INTEGER PRIMARY KEY," +
                "NM VARCHAR(80) NOT NULL," +
                "CPF VARCHAR(12) UNIQUE NOT NULL," +
                "ESPEC VARCHAR(50) NOT NULL)";

        String cm4 = "CREATE TABLE " + SERVICO + " (" +
                "ID INTEGER PRIMARY KEY," +
                "CPF_CLI VARCHAR(12) NOT NULL," +
                "CPF_SOC VARCHAR(100) NOT NULL," +
                "NM VARCHAR(80) NOT NULL," +
                "CNPJ VARCHAR(18) NOT NULL," +
                "TEL VARCHAR(15) NOT NULL," +
                "ENDE VARCHAR(130) NOT NULL," +
                "EMAIL VARCHAR(80) NOT NULL," +
                "SERVICO VARCHAR(80) NOT NULL," +
                "SISTEMA VARCHAR(80) NOT NULL," +
                "DESCR VARCHAR(80) NOT NULL," +
                "VALOR DECIMAL(10,2) NOT NULL," +
                "DT_PRAZO DATE NOT NULL," +
                "DT_INI DATE NOT NULL," +
                "FOREIGN KEY(CPF_CLI) REFERENCES "+CLIENTE+"(CPF),"+
                "FOREIGN KEY(CPF_SOC) REFERENCES "+SOCIO+"(CPF))";

        String cm5 = "CREATE TABLE " + CONTRATO + " (" +
                "ID INTEGER PRIMARY KEY," +
                "CPF_CLI VARCHAR(12) NOT NULL," +
                "CPF_SOC VARCHAR(100) NOT NULL," +
                "NM VARCHAR(80) NOT NULL," +
                "CNPJ VARCHAR(18) NOT NULL," +
                "TEL VARCHAR(15) NOT NULL," +
                "ENDE VARCHAR(130) NOT NULL," +
                "EMAIL VARCHAR(80) NOT NULL," +
                "SERVICO VARCHAR(80) NOT NULL," +
                "SISTEMA VARCHAR(80) NOT NULL," +
                "DESCR VARCHAR(80) NOT NULL," +
                "VALOR DECIMAL(10,2) NOT NULL," +
                "DT_PRAZO DATE NOT NULL," +
                "DT_INI DATE NOT NULL)";
        db.execSQL(cm1);
        db.execSQL(cm2);
        db.execSQL(cm3);
        db.execSQL(cm4);
        db.execSQL(cm5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long Insert(DtoCliente dtoCliente) {
        ContentValues values = new ContentValues();
        values.put("NM", dtoCliente.getNm());
        values.put("CPF", dtoCliente.getCpf());
        values.put("RG", dtoCliente.getRg());
        values.put("TEL", dtoCliente.getTel());
        values.put("ENDE", dtoCliente.getEnde());
        values.put("EMAIL", dtoCliente.getEmail());
        values.put("DT", dtoCliente.getDt());
        return getWritableDatabase().insert(CLIENTE, null, values);
    }

    public void Insert(DtoSocio dtoSocio) {
        ContentValues values = new ContentValues();
        values.put("NM", dtoSocio.getNm());
        values.put("CPF", dtoSocio.getCpf());
        values.put("ESPEC", dtoSocio.getEspec());
        getWritableDatabase().insert(SOCIO, null, values);
    }

    public ArrayList<DtoCliente> Select(String cpf) {
        String cm = "SELECT * FROM " + CLIENTE;
        Cursor cursor = getReadableDatabase().rawQuery(cm, null);
        ArrayList<DtoCliente> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            DtoCliente dtoCliente = new DtoCliente();
            dtoCliente.setId(cursor.getInt(0));
            dtoCliente.setNm(cursor.getString(1));
            dtoCliente.setCpf(cursor.getString(2));
            dtoCliente.setRg(cursor.getString(3));
            dtoCliente.setTel(cursor.getString(4));
            dtoCliente.setEnde(cursor.getString(5));
            dtoCliente.setEmail(cursor.getString(6));
            dtoCliente.setDt(cursor.getString(7));

            arrayList.add(dtoCliente);
        }
        return arrayList;
    }

    public void Insert(DtoLogin dtoLogin) {
        ContentValues values = new ContentValues();
        values.put("LOGIN", dtoLogin.getLogin());
        values.put("SENHA", dtoLogin.getSenha());
        getWritableDatabase().insert(LOGIN,null, values);
    }


    public boolean Select(String usuario, String senha) {
        String cm = "SELECT * FROM "+LOGIN+" WHERE LOGIN = ? AND SENHA = ?";
        String[] args = {usuario,senha};
        Cursor cursor = getReadableDatabase().rawQuery(cm,args);

        return cursor.moveToNext();
    }

    public boolean SelectCPF(String cpf) {
        String cm = "SELECT * FROM "+CLIENTE+" WHERE CPF = ?";
        String[] args = {cpf};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        return cursor.moveToNext();
    }

    public ArrayList<DtoCliente> Select() {
        String cm = "SELECT * FROM "+CLIENTE;
        Cursor cursor = getReadableDatabase().rawQuery(cm, null);
        ArrayList<DtoCliente> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoCliente dtoCliente = new DtoCliente();
            dtoCliente.setId(cursor.getInt(0));
            dtoCliente.setNm(cursor.getString(1));
            dtoCliente.setCpf(cursor.getString(2));
            dtoCliente.setRg(cursor.getString(3));
            dtoCliente.setTel(cursor.getString(4));
            dtoCliente.setEnde(cursor.getString(5));
            dtoCliente.setEmail(cursor.getString(6));
            dtoCliente.setDt(cursor.getString(7));
            arrayList.add(dtoCliente);
        }
        return arrayList;
    }

    public ArrayList<DtoCliente> Like(String nome) {
        String cm = "SELECT * FROM "+CLIENTE+" WHERE NM LIKE ?";
        String[] args = {'%'+nome+'%'};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        ArrayList<DtoCliente> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoCliente dtoCliente = new DtoCliente();
            dtoCliente.setId(cursor.getInt(0));
            dtoCliente.setNm(cursor.getString(1));
            dtoCliente.setCpf(cursor.getString(2));
            dtoCliente.setRg(cursor.getString(3));
            dtoCliente.setTel(cursor.getString(4));
            dtoCliente.setEnde(cursor.getString(5));
            dtoCliente.setEmail(cursor.getString(6));
            dtoCliente.setDt(cursor.getString(7));
            arrayList.add(dtoCliente);
        }
        return arrayList;
    }

    public long InsertServico(DtoServico dtoServico) {
        ContentValues values = new ContentValues();
        values.put("CPF_CLI", dtoServico.getCpf());
        values.put("CPF_SOC", dtoServico.getEspc());
        values.put("NM", dtoServico.getNm());
        values.put("CNPJ", dtoServico.getCnpj());
        values.put("TEL", dtoServico.getTel());
        values.put("ENDE", dtoServico.getEnde());
        values.put("EMAIL", dtoServico.getEmail());
        values.put("SERVICO", dtoServico.getServico());
        values.put("SISTEMA", dtoServico.getSistema());
        values.put("DESCR", dtoServico.getDesc());
        values.put("VALOR", dtoServico.getValor());
        values.put("DT_INI", dtoServico.getDtInicio());
        values.put("DT_PRAZO", dtoServico.getDtPrazo());
        return getWritableDatabase().insert(SERVICO, null, values);
    }

    public long InsertContrato(DtoServico dtoServico) {
        ContentValues values = new ContentValues();
        values.put("CPF_CLI", dtoServico.getCpf());
        values.put("CPF_SOC", dtoServico.getEspc());
        values.put("NM", dtoServico.getNm());
        values.put("CNPJ", dtoServico.getCnpj());
        values.put("TEL", dtoServico.getTel());
        values.put("ENDE", dtoServico.getEnde());
        values.put("EMAIL", dtoServico.getEmail());
        values.put("SERVICO", dtoServico.getServico());
        values.put("SISTEMA", dtoServico.getSistema());
        values.put("DESCR", dtoServico.getDesc());
        values.put("VALOR", dtoServico.getValor());
        values.put("DT_INI", dtoServico.getDtInicio());
        values.put("DT_PRAZO", dtoServico.getDtPrazo());
        return getWritableDatabase().insert(CONTRATO, null, values);
    }
    public long Update(DtoCliente dtoCliente, int id) {
        String cm = "ID=?";
        String[] args = {id+""};
        ContentValues values = new ContentValues();
        values.put("NM", dtoCliente.getNm());
        values.put("CPF", dtoCliente.getCpf());
        values.put("RG", dtoCliente.getRg());
        values.put("TEL", dtoCliente.getTel());
        values.put("ENDE", dtoCliente.getEnde());
        values.put("EMAIL", dtoCliente.getEmail());
        values.put("DT", dtoCliente.getDt());
        return getWritableDatabase().update(CLIENTE, values, cm, args);
    }

    public int Excluir(int iid) {
        String cm = "ID=?";
        String[] args = {iid+""};
        return getWritableDatabase().delete(CLIENTE, cm, args);
    }

    public boolean SelectCPF_(String cpf) {
        String cm = "SELECT * FROM "+SERVICO+" WHERE CPF_CLI = ?";
        String[] args = {cpf};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        return cursor.moveToNext();
    }

    public ArrayList<DtoSocio> Select(DtoSocio dtoSocio) {

        String cm = "SELECT * FROM "+SOCIO;
        Cursor cursor = getReadableDatabase().rawQuery(cm, null);
        ArrayList<DtoSocio> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoSocio dto = new DtoSocio();
            dto.setId(cursor.getInt(0));
            dto.setNm(cursor.getString(1));
            dto.setCpf(cursor.getString(2));
            dto.setEspec(cursor.getString(3));
            arrayList.add(dto);
        }
        return arrayList;
    }

    public ArrayList<DtoSocio> LikeAgenda(String nome) {
        String cm = "SELECT * FROM "+SOCIO+" WHERE NM LIKE ?";
        String[] args = {'%'+nome+'%'};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        ArrayList<DtoSocio> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoSocio dto = new DtoSocio();
            dto.setId(cursor.getInt(0));
            dto.setNm(cursor.getString(1));
            dto.setCpf(cursor.getString(2));
            dto.setEspec(cursor.getString(3));
            arrayList.add(dto);
        }
        return arrayList;
    }

    public ArrayList<DtoServico> SelectServico() {
        String cm = "SELECT * FROM " + SERVICO;
        Cursor cursor = getReadableDatabase().rawQuery(cm, null);
        ArrayList<DtoServico> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            DtoServico dtoServico = new DtoServico();
            dtoServico.setId(cursor.getInt(0));
            dtoServico.setCpf(cursor.getString(1));
            dtoServico.setEspc(cursor.getString(2));
            dtoServico.setNm(cursor.getString(3));
            dtoServico.setCnpj(cursor.getString(4));
            dtoServico.setTel(cursor.getString(5));
            dtoServico.setEnde(cursor.getString(6));
            dtoServico.setEmail(cursor.getString(7));
            dtoServico.setServico(cursor.getString(8));
            dtoServico.setSistema(cursor.getString(9));
            dtoServico.setDesc(cursor.getString(10));
            dtoServico.setValor(cursor.getDouble(11));
            dtoServico.setDtPrazo(cursor.getString(12));
            dtoServico.setDtInicio(cursor.getString(13));

            arrayList.add(dtoServico);
        }
        return arrayList;
    }

    public ArrayList<DtoServico> LikeServico(String nome) {
        String cm = "SELECT * FROM "+SERVICO+" WHERE NM LIKE ?";
        String[] args = {'%'+nome+'%'};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        ArrayList<DtoServico> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoServico dto = new DtoServico();
            DtoServico dtoServico = new DtoServico();
            dtoServico.setId(cursor.getInt(0));
            dtoServico.setCpf(cursor.getString(1));
            dtoServico.setEspc(cursor.getString(2));
            dtoServico.setNm(cursor.getString(3));
            dtoServico.setCnpj(cursor.getString(4));
            dtoServico.setTel(cursor.getString(5));
            dtoServico.setEnde(cursor.getString(6));
            dtoServico.setEmail(cursor.getString(7));
            dtoServico.setServico(cursor.getString(8));
            dtoServico.setSistema(cursor.getString(9));
            dtoServico.setDesc(cursor.getString(10));
            dtoServico.setValor(cursor.getDouble(11));
            dtoServico.setDtPrazo(cursor.getString(12));
            dtoServico.setDtInicio(cursor.getString(13));

            arrayList.add(dtoServico);
        }
        return arrayList;
    }

    public long Update(DtoServico dtoServico, int id) {
        String iid = "ID=?";
        String[] args = {""+id};
        ContentValues values = new ContentValues();
        values.put("CPF_CLI", dtoServico.getCpf());
        values.put("CPF_SOC", dtoServico.getEspc());
        values.put("NM", dtoServico.getNm());
        values.put("CNPJ", dtoServico.getCnpj());
        values.put("TEL", dtoServico.getTel());
        values.put("ENDE", dtoServico.getEnde());
        values.put("EMAIL", dtoServico.getEmail());
        values.put("SERVICO", dtoServico.getServico());
        values.put("SISTEMA", dtoServico.getSistema());
        values.put("DESCR", dtoServico.getDesc());
        values.put("VALOR", dtoServico.getValor());
        values.put("DT_INI", dtoServico.getDtInicio());
        values.put("DT_PRAZO", dtoServico.getDtPrazo());
        return getWritableDatabase().update(SERVICO, values, iid, args);
    }

    public int ExcluirServico(int id) {
        String iid= "ID=?";
        String[] args = {""+id};
        return getWritableDatabase().delete(SERVICO, iid, args);
    }

    public ArrayList<DtoServico> SelectServico(String cpf) {
        String cm = "SELECT * FROM " + SERVICO+" WHERE CPF_SOC LIKE ?";
        String[] args = {'%'+cpf+'%'};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        ArrayList<DtoServico> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            DtoServico dtoServico = new DtoServico();
            dtoServico.setId(cursor.getInt(0));
            dtoServico.setCpf(cursor.getString(1));
            dtoServico.setEspc(cursor.getString(2));
            dtoServico.setNm(cursor.getString(3));
            dtoServico.setCnpj(cursor.getString(4));
            dtoServico.setTel(cursor.getString(5));
            dtoServico.setEnde(cursor.getString(6));
            dtoServico.setEmail(cursor.getString(7));
            dtoServico.setServico(cursor.getString(8));
            dtoServico.setSistema(cursor.getString(9));
            dtoServico.setDesc(cursor.getString(10));
            dtoServico.setValor(cursor.getDouble(11));
            dtoServico.setDtPrazo(cursor.getString(12));
            dtoServico.setDtInicio(cursor.getString(13));

            arrayList.add(dtoServico);
        }
        return arrayList;
    }

    public ArrayList<DtoServico> LikeServico(String nome, String cpf) {
        String cm = "SELECT * FROM "+SERVICO+" WHERE NM LIKE ? AND CPF_SOC  LIKE ?";
        String[] args = {'%'+nome+'%','%'+cpf+'%'};
        Cursor cursor = getReadableDatabase().rawQuery(cm, args);
        ArrayList<DtoServico> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            DtoServico dto = new DtoServico();
            DtoServico dtoServico = new DtoServico();
            dtoServico.setId(cursor.getInt(0));
            dtoServico.setCpf(cursor.getString(1));
            dtoServico.setEspc(cursor.getString(2));
            dtoServico.setNm(cursor.getString(3));
            dtoServico.setCnpj(cursor.getString(4));
            dtoServico.setTel(cursor.getString(5));
            dtoServico.setEnde(cursor.getString(6));
            dtoServico.setEmail(cursor.getString(7));
            dtoServico.setServico(cursor.getString(8));
            dtoServico.setSistema(cursor.getString(9));
            dtoServico.setDesc(cursor.getString(10));
            dtoServico.setValor(cursor.getDouble(11));
            dtoServico.setDtPrazo(cursor.getString(12));
            dtoServico.setDtInicio(cursor.getString(13));

            arrayList.add(dtoServico);
        }
        return arrayList;
    }
}
