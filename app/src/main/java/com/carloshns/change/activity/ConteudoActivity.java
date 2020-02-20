package com.carloshns.change.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carloshns.change.R;
import com.carloshns.change.entities.Quedas;
import com.carloshns.change.helper.Cesar;
import com.carloshns.change.helper.QuedasDAO;

import java.util.List;

public class ConteudoActivity extends AppCompatActivity {

    private Button botaoCriptografado;
    private Button botaoDescriptografado;
    private Button botaoEditar;
    private TextView textView;
    private EditText textoChave;
    private Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        dados = getIntent().getExtras();
        textView = findViewById(R.id.texto_conteudo);
        botaoCriptografado = findViewById(R.id.botao_criptografado);
        botaoDescriptografado = findViewById(R.id.botao_descriptografado);
        botaoEditar = findViewById(R.id.botao_editar);
        textoChave = findViewById(R.id.texto_chave);

        botaoCriptografado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = Cesar.encriptar(1, dados.getString("descricao"));
                textView.setText(descricao);
            }
        });

        botaoDescriptografado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String chave = textoChave.getText().toString();

                if (chave.equals("2454")){
                    textView.setText(dados.getString("descricao"));
                } else {
                    Toast.makeText(getApplicationContext(), "Chave Inválida", Toast.LENGTH_SHORT).show();
                    textView.setText("");
                }

            }
        });

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String chave = textoChave.getText().toString();

                if (chave.equals("2454")){
                    textView.setText(dados.getString("descricao"));
                    Intent intent = new Intent(getApplicationContext(), CadastrarEditarActivity.class);
                    intent.putExtra("id", dados.getLong("id"));
                    intent.putExtra("descricao", dados.getString("descricao"));
                    intent.putExtra("data", dados.getLong("data"));
                    //intent.putExtra("goto", "ConteudoActivity");
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Chave Inválida", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    protected void onStart() {

        QuedasDAO quedasDAO = new QuedasDAO(getApplicationContext());
        List<Quedas> quedas = quedasDAO.quedas();

        int count = 0;
        while (count < quedas.size()){

            Quedas queda = new Quedas();
            queda = quedas.get(count);

            if (queda.getId().equals(dados.getLong("id"))){
                if (!queda.getDescricao().equals(dados.get("descricao"))){
                    finish();
                }
            }

            count++;
        }



        super.onStart();
    }
}
