package br.com.diaristaslimpo.limpo.Controler.telas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.Controler.webservice.EnviaServicos;

public class TelaServico extends AppCompatActivity {
    private ImageButton btlimpeza;
    private ImageButton btpassaroupa;
    private ImageButton btlavarroupa;
    private Button btdata;
    private EditText edtoutros;
    private String end, servs, idEndereco;
    private int limpeza,passarroupa,lavarroupa;
    private int ativo,inativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);
        limpeza= 0;
        passarroupa=0;
        lavarroupa=0;
        ativo = Color.parseColor("#c60b2d");
        inativo = Color.parseColor("#DCDCDC");


        btlimpeza = (ImageButton) findViewById(R.id.bt_limpeza);
        btlimpeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(limpeza==0){
                    limpeza=1;
                    btlimpeza.setBackgroundColor(ativo);
                }else{
                    limpeza=0;
                    btlimpeza.setBackgroundColor(inativo);
                }
            }
        });
        btpassaroupa = (ImageButton)findViewById(R.id.bt_passarroupa);
        btpassaroupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passarroupa==0){
                    passarroupa=1;
                    btpassaroupa.setBackgroundColor(ativo);
                }else{
                    passarroupa=0;
                    btpassaroupa.setBackgroundColor(inativo);
                }
            }
        });

        btlavarroupa = (ImageButton)findViewById(R.id.bt_lavarroupa);
        btlavarroupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lavarroupa==0){
                    lavarroupa=1;
                    btlavarroupa.setBackgroundColor(ativo);
                }else{
                    lavarroupa=0;
                    btlavarroupa.setBackgroundColor(inativo);
                }
            }
        });

        edtoutros = (EditText)findViewById(R.id.edt_outros);



        btdata = (Button)findViewById(R.id.btData);
        btdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicos();
                new EnviaServicos(TelaServico.this).execute(String.valueOf(limpeza),String.valueOf(passarroupa),String.valueOf(lavarroupa));
            }
        });

    }
    public String servicos(){
        if (limpeza==1){
            servs = "Limpeza\n";
        }
        if(passarroupa==1){
            servs += "Passar Roupa\n";
        }
        if (lavarroupa==1){
            servs += "Lavar Roupa";
        }
        servs = servs + "\n" + edtoutros.getText().toString();
        return servs;
    }
}
