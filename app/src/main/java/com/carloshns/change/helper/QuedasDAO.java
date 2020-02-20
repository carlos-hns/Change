package com.carloshns.change.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.carloshns.change.entities.Quedas;

import java.util.ArrayList;
import java.util.List;

public class QuedasDAO implements IQuedasDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private List<Quedas> quedas = new ArrayList<>();

    public QuedasDAO (Context context){
        DBHelper db = new DBHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Quedas queda) {

        ContentValues dados = new ContentValues();
        dados.put( DBHelper.QUEDAS_DESCRICAO, queda.getDescricao() );
        dados.put( DBHelper.QUEDAS_DATA, queda.getData());

        try {
            escrever.insert(DBHelper.TABELA_QUEDAS, null, dados);
        } catch (Exception e) {
            Log.i("INFO DB", "FALHA AO INSERIR LINHA NA TABELA" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Quedas queda) {

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.QUEDAS_DESCRICAO, queda.getDescricao());
        String[] parametros = {queda.getId().toString()};

        escrever.update(DBHelper.TABELA_QUEDAS, cv, "id=?", parametros);
        return true;
    }

    @Override
    public boolean deletar(Quedas queda) {

        String[] args = {queda.getId().toString()};
        escrever.delete(DBHelper.TABELA_QUEDAS, "id=?", args);

        return true;
    }

    @Override
    public List<Quedas> quedas() {

        List<Quedas> quedas = new ArrayList<>();

        String sql = ("SELECT * FROM " + DBHelper.TABELA_QUEDAS + " WHERE 1=1 ORDER BY data DESC ");
        Cursor cursor = ler.rawQuery(sql, null);

        while ( cursor.moveToNext() ){

            Quedas queda = new Quedas();

            Long id = cursor.getLong(cursor.getColumnIndex(DBHelper.QUEDAS_ID));
            String data = cursor.getString(cursor.getColumnIndex(DBHelper.QUEDAS_DATA));
            String desc = cursor.getString(cursor.getColumnIndex(DBHelper.QUEDAS_DESCRICAO));

            queda.setId(id);
            queda.setData(data);
            queda.setDescricao(desc);

            quedas.add(queda);
        }

        return quedas;
    }
}
