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

public class TelaSEndereco extends AppCompatActivity implements View.OnClickListener {
    private ListView lst;
    private Button btn;
    private ArrayList<ObjEndereco> itens;
    private ArrayList<ObjEndereco> select;
    private AdapterEndereco adapterListView;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    Activity at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_endereco);
        at=this;
        lst = (ListView) findViewById(R.id.lstEndereco);
        btn = (Button) findViewById(R.id.btEndereco);
        btn.setOnClickListener(this);

        itens = new ArrayList<>();
        select = new ArrayList<>();
      //  itens.add( new ObjEndereco("Rua dos pampas", "006624155", "jd veloso", R.mipmap.iconapp));

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

                Intent intent = new Intent(at, TelaServico.class);
                intent.putExtra("ENDERECO", itens.get(position).getEndereco());
                startActivity(intent);


            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,TelaCadastroEndereco.class);
        intent.putExtra("chave","telaendereco");
        startActivity(intent);
        finish();

    }
}
