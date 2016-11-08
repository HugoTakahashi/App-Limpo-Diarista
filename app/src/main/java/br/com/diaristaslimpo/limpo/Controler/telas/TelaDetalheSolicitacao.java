package br.com.diaristaslimpo.limpo.Controler.telas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.diaristaslimpo.limpo.R;

public class TelaDetalheSolicitacao extends AppCompatActivity {
    private String nomeCliente,dataDiaria,cidade,bairro,endereco,observacao,cep;
    private int idSolicitacao,numero,limpeza,passarRoupa,lavarRoupa;
    private TextView txtNome,txtData,txtEndereco,txtServicos;
    private Button btMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_solicitacao);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nomeCliente = bundle.getString("nomecliente");
        dataDiaria = bundle.getString("datadiaria");
        cidade = bundle.getString("cidade");
        bairro = bundle.getString("bairro");
        idSolicitacao = bundle.getInt("idsolicitacao");
        endereco = bundle.getString("endereco");
        numero = bundle.getInt("numero");
        cep = bundle.getString("cep");
        limpeza = bundle.getInt("limpeza");
        passarRoupa = bundle.getInt("passarroupa");
        lavarRoupa = bundle.getInt("lavarroupa");
        observacao = bundle.getString("observacao");
        txtNome = (TextView)findViewById(R.id.txtNomeCliente);
        txtData = (TextView)findViewById(R.id.txtDataDiaria);
        txtEndereco = (TextView)findViewById(R.id.txtEndereco);
        txtServicos = (TextView)findViewById(R.id.txtServicos);

        txtNome.setText(nomeCliente);
        txtData.setText(dataDiaria);
        String txt1 = endereco + ", " + numero + ", " + cidade;
        txtEndereco.setText(txt1);
        btMapa = (Button)findViewById(R.id.btMapa);
        btMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itMapa = new Intent(Intent.ACTION_VIEW);
                itMapa.setData(Uri.parse("geo:0:0?q="+ txtEndereco.getText().toString()));
                startActivity(itMapa);
            }
        });
        String txt2 = limpeza + ", " + passarRoupa + ", " + lavarRoupa + ", " + observacao;
        txtServicos.setText(txt2);


    }
}
