package com.example.techonehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.techonehub.Dto.DtoCliente;
import com.example.techonehub.Dto.DtoLogin;
import com.example.techonehub.Dto.DtoSocio;

import java.util.ArrayList;

public class DaoTechOneHub extends SQLiteOpenHelper {

    final String CLIENTE = "TBL_CLIENTE";
    final String SOCIO = "TBL_SOCIO";
    final String LOGIN = "TBL_LOGIN";

    public DaoTechOneHub(@Nullable Context context) {
        super(context, "DB_TESTE4", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String lm = "CREATE TABLE " + LOGIN + " (" +
                "ID INTEGER PRIMARY KEY," +
                "LOGIN VARCHAR(80) NOT NULL," +
                "SENHA VARCHAR(11) NOT NULL)";

        String cm = "CREATE TABLE " + CLIENTE + " (" +
                "ID INTEGER PRIMARY KEY," +
                "NM VARCHAR(80) NOT NULL," +
                "CPF VARCHAR(12) UNIQUE NOT NULL," +
                "RG VARCHAR(12) NOT NULL," +
                "TEL VARCHAR(15) NOT NULL," +
                "ENDE VARCHAR(130) NOT NULL," +
                "EMAIL VARCHAR(80) NOT NULL," +
                "DT DATE NOT NULL)";

        String sm = "CREATE TABLE " + SOCIO + " (" +
                "ID INTEGER PRIMARY KEY," +
                "NM VARCHAR(80) NOT NULL," +
                "CPF VARCHAR(12) UNIQUE NOT NULL," +
                "ESPEC VARCHAR(50) NOT NULL)";

        db.execSQL(lm);
        db.execSQL(cm);
        db.execSQL(sm);
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

    public long Insert(DtoSocio dtoSocio) {
        ContentValues values = new ContentValues();
        values.put("NM", dtoSocio.getNm());
        values.put("CPF", dtoSocio.getCpf());
        values.put("ESPEC", dtoSocio.getEspec());
        return getWritableDatabase().insert(SOCIO, null, values);
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
}
