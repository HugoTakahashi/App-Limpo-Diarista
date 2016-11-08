package br.com.diaristaslimpo.limpo.Controler.telas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjOrcamento;
import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.Model.helper.AdapterOrcamento;
import br.com.diaristaslimpo.limpo.Controler.webservice.RecebeHistorico;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;

public class Tela_Historico extends AppCompatActivity {

    private ListView lstOrcamento;
    private ArrayList<ObjOrcamento> itens;
    private AdapterOrcamento adapterListView;
    private DataBase dataBase;
    private SQLiteDatabase conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);
        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);

        lstOrcamento = (ListView) findViewById(R.id.lstOrcamento);
        itens = new ArrayList<>();
        String json = String.valueOf(scriptSQL.retornaIdDiarista());
        new RecebeHistorico(this).execute(json);


        ArrayList<ObjOrcamento> obj = scriptSQL.selectListaHistorico();
        adapterListView = new AdapterOrcamento(this, obj);
        lstOrcamento.setAdapter(adapterListView);


        lstOrcamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent it = new Intent(Tela_Historico.this,Tela_DetalheHistorico.class);
                startActivity(it);

            }

        });

    }
    }