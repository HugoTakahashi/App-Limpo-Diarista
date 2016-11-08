package br.com.diaristaslimpo.limpo.Controler.telas;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjOrcamento;
import br.com.diaristaslimpo.limpo.Model.helper.AdapterProxDiaria;
import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;

public class Tela_Prox_Diarias extends AppCompatActivity {
    private ListView lst;
    private AdapterProxDiaria adapterListView;
    private DataBase dataBase;
    private SQLiteDatabase conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__prox__diarias);
        lst = (ListView) findViewById(R.id.lst_proxdia);
        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);
        ArrayList<ObjOrcamento> obj = scriptSQL.selectListaHistorico();
        adapterListView = new AdapterProxDiaria(this, obj);
        lst.setAdapter(adapterListView);
    }
}
