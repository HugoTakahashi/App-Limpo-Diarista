package br.com.diaristaslimpo.limpo.Controler.telas;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjEndereco;
import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;
import br.com.diaristaslimpo.limpo.Model.helper.AdapterEndereco;

public class Tela_MeusEnderecos extends AppCompatActivity {
    private ListView lst;
    private Button btn;
    private AdapterEndereco adapterListView;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    Activity at;
    private ArrayList<ObjEndereco> itens;
    private ArrayList<ObjEndereco> select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__meus_enderecos);

        at=this;
        lst = (ListView) findViewById(R.id.lstmeusenderecos);
        btn = (Button) findViewById(R.id.bt_meusendadd);

        itens = new ArrayList<>();
        select = new ArrayList<>();

        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);
        select=scriptSQL.selectEndereço();
        for (int i = 0;i<select.size();i++){
            itens.add(select.get(i));
        }
        adapterListView = new AdapterEndereco(this, itens);
        lst.setAdapter(adapterListView);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(at, TelaCadastroEndereco.class);
                intent.putExtra("chave","1");
                intent.putExtra("idendereco",itens.get(position).getIdEndereco());
                intent.putExtra("identificacaoendereco",itens.get(position).getIdentificacaoEndereco());
                intent.putExtra("cep",itens.get(position).getCep());
                intent.putExtra("endereco",itens.get(position).getEndereco());
                intent.putExtra("numero",itens.get(position).getNumero());
                intent.putExtra("complemento",itens.get(position).getComplemento());
                intent.putExtra("bairro",itens.get(position).getBairro());
                intent.putExtra("cidade",itens.get(position).getCidade());
                intent.putExtra("pontoreferencia",itens.get(position).getPontoreferencia());

                startActivity(intent);


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(at,TelaCadastroEndereco.class);
                intent.putExtra("chave","0");
                startActivity(intent);
            }
        });
    }
}

