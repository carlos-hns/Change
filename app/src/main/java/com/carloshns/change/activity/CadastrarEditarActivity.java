package com.carloshns.change.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carloshns.change.R;
import com.carloshns.change.entities.Quedas;
import com.carloshns.change.helper.QuedasDAO;
import com.google.android.material.textfield.TextInputEditText;


public class CadastrarEditarActivity extends AppCompatActivity {

    private Button botaoEnviar;
    private TextInputEditText textoMotivo;
    private Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_editar);

        getSupportActionBar().setTitle("Cadastrar / Editar");
        botaoEnviar = findViewById(R.id.botaoEnviar);
        textoMotivo = findViewById(R.id.textoMotivo);

        dados = getIntent().getExtras();

        if (dados != null){

            String descricao = dados.getString("descricao");
            textoMotivo.setText(descricao);
        }

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String descricao = textoMotivo.getText().toString();

                if (dados != null){

                    QuedasDAO quedasDAO = new QuedasDAO(getApplicationContext());
                    Quedas queda = new Quedas();
                    queda.setId(dados.getLong("id"));
                    queda.setData(dados.getString("data"));

                    if ( !descricao.isEmpty() ){

                        queda.setDescricao(descricao);
                        quedasDAO.atualizar(queda);
                        finish();
                    }


                } else {
                    descricao = textoMotivo.getText().toString();
                    if ( !descricao.isEmpty() ){

                        QuedasDAO quedas = new QuedasDAO( getApplicationContext() );
                        Quedas queda = new Quedas(descricao);
                        quedas.salvar( queda );
                        finish();
                    }


                }
            }
        });

    }
}
