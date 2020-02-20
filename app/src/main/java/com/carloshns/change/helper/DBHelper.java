package com.carloshns.change.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "CHANGE_DB";

    public static String TABELA_QUEDAS = "quedas";
    public static String QUEDAS_DESCRICAO = "descricao";
    public static String QUEDAS_DATA = "data";
    public static String QUEDAS_ID = "id";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_QUEDAS + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL, " +
                "data TEXT NOT NULL ); ";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR A TABELA");
        } catch (Exception e) {
            Log.i("INFO DB", "FALHA AO CRIAR A TABELA" + e.getMessage());
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
