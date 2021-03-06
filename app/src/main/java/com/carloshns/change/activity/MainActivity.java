package com.carloshns.change.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.carloshns.change.R;
import com.carloshns.change.adapter.AdapterFalha;
import com.carloshns.change.entities.Quedas;
import com.carloshns.change.helper.QuedasDAO;
import com.carloshns.change.helper.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {


                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Deseja realmente exlcuir esse item? ");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                QuedasDAO quedasDAO = new QuedasDAO(getApplicationContext());

                                List<Quedas> quedas = quedasDAO.quedas();
                                quedasDAO.deletar(quedas.get(position));
                                configurarRecycler();
                                Toast.makeText(getApplicationContext(), "Item Removido", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.setNegativeButton("Não", null);
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        QuedasDAO quedasDAO = new QuedasDAO(getApplicationContext());
                        List<Quedas> quedas = quedasDAO.quedas();

                        Quedas queda = quedas.get(position);

                        Intent intent = new Intent( getApplicationContext(), ConteudoActivity.class );
                        intent.putExtra("id", queda.getId());
                        intent.putExtra("descricao", queda.getDescricao());
                        intent.putExtra("data", queda.getData());

                        startActivity(intent);
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), CadastrarEditarActivity.class );
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        configurarRecycler();
        super.onStart();
    }

    public void configurarRecycler(){

        RecyclerView.LayoutManager layout = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager(layout);

        QuedasDAO quedasDAO = new QuedasDAO(getApplicationContext());

        List<Quedas> quedas = quedasDAO.quedas();

        AdapterFalha adapter = new AdapterFalha( quedas );
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.info:
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
