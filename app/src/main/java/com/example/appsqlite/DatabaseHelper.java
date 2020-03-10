package com.example.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {

        super(context, "DBLogin.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbUsuario(cpf text primary key, senha text, email text, rg text," +
                "nome text, telefone text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbUsuario");
    }
    //inserindo valores no banco de dados

    public boolean insert(String cpf, String senha, String email, String rg,
                           String telefone, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("cpf", cpf);
        contentValues.put("email", email);
        contentValues.put("telefone", telefone);
        contentValues.put("senha", senha);
        contentValues.put("nome", nome);
        contentValues.put("rg", rg);

        long inserido = db.insert("tbUsuario", null, contentValues);

        if (inserido == 1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validarCPF(String cpf) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbUsuario where cpf = ?", new String[]{cpf});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }

    //Verificando usuário e senha

    public Boolean ChecarCPFeSenha (String cpf, String senha) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tbUsuario where cpf = ? and senha = ?", new String[]{cpf, senha});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }
    public List<Usuarios> listarTodosClientes() {

        List<Usuarios> listarClientes = new ArrayList<Usuarios>();

        String query = "SELECT * FROM " + tbUsuario;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Usuarios usuarios = new Usuarios();
                usuarios.setCodigo(Integer.parseInt(cursor.getString(0)));
                usuarios.setNome(cursor.getString(1));
                usuarios.setEmail(cursor.getString(3));

                listarClientes.add(usuarios);

            } while (cursor.moveToNext());
        }


        return listarClientes;
    }

}

