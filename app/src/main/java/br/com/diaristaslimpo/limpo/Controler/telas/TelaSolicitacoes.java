package br.com.diaristaslimpo.limpo.Controler.telas;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjListaSolicitacoes;
import br.com.diaristaslimpo.limpo.Model.helper.AdapterListaSolicitacoes;
import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;

public class TelaSolicitacoes extends AppCompatActivity {
    private ListView lst;
    private ArrayList<ObjListaSolicitacoes> itens;
    private String id;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private AdapterListaSolicitacoes adapterListView;
    Activity at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_solicitacao);

        at=this;
        lst = (ListView) findViewById(R.id.lstDiarista);
        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);

        ArrayList<ObjListaSolicitacoes> obj;
        itens = new ArrayList<>();
        obj = scriptSQL.selectListaSolicitacoes();
        ObjListaSolicitacoes lista = new ObjListaSolicitacoes();
        for (int i=0;i<obj.size();i++){
            lista.setNomeCliente(obj.get(i).getNomeCliente());
            lista.setDataDiaria(obj.get(i).getDataDiaria());
            lista.setCidade(obj.get(i).getCidade());
            lista.setBairro(obj.get(i).getBairro());
            lista.setIdSolicitacao(obj.get(i).getIdSolicitacao());
            lista.setEndereco(obj.get(i).getEndereco());
            lista.setNumero(obj.get(i).getNumero());
            lista.setCep(obj.get(i).getCep());
            lista.setLimpeza(obj.get(i).getLimpeza());
            lista.setPassarRoupa(obj.get(i).getPassarRoupa());
            lista.setLavarRoupa(obj.get(i).getLavarRoupa());
            lista.setObservacao(obj.get(i).getObservacao());
            itens.add(lista);
            lista = new ObjListaSolicitacoes();
        }

        adapterListView = new AdapterListaSolicitacoes(this, itens);
        lst.setAdapter(adapterListView);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(TelaSolicitacoes.this,TelaDetalheSolicitacao.class);
                it.putExtra("nomecliente",itens.get(position).getNomeCliente());
                it.putExtra("datadiaria",itens.get(position).getDataDiaria());
                it.putExtra("cidade",itens.get(position).getCidade());
                it.putExtra("bairro",itens.get(position).getBairro());
                it.putExtra("idsolicitacao",itens.get(position).getIdSolicitacao());
                it.putExtra("endereco",itens.get(position).getEndereco());
                it.putExtra("numero",itens.get(position).getNumero());
                it.putExtra("cep",itens.get(position).getCep());
                it.putExtra("limpeza",itens.get(position).getLimpeza());
                it.putExtra("passarroupa",itens.get(position).getPassarRoupa());
                it.putExtra("lavarroupa",itens.get(position).getLavarRoupa());
                it.putExtra("observacao",itens.get(position).getObservacao());
                startActivity(it);


            }
        });
    }
}
