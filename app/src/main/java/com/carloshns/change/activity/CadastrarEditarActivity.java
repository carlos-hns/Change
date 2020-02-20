package com.carloshns.change.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carloshns.change.R;
import com.carloshns.change.entities.Quedas;
import com.carloshns.change.helper.QuedasDAO;


public class CadastrarEditarActivity extends AppCompatActivity {

    private Button botaoEnviar;
    private TextView textoMotivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_editar);

        getSupportActionBar().setTitle("Cadastrar / Editar");
        botaoEnviar = findViewById(R.id.botaoEnviar);
        textoMotivo = findViewById(R.id.textoMotivo);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String descricao = textoMotivo.getText().toString();
                if ( !descricao.isEmpty() ){

                    QuedasDAO quedas = new QuedasDAO( getApplicationContext() );
                    Quedas queda = new Quedas(descricao);
                    quedas.salvar( queda );
                    finish();
                }

            }
        });

    }
}
