package br.com.diaristaslimpo.limpo.Controler.telas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.Model.helper.DateUtils;

public class TelaSDataServico extends AppCompatActivity implements View.OnClickListener {
    private EditText edt;
    private Button bt;
    private String end, servs, dataServico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_data_servico);
        edt = (EditText)findViewById(R.id.data);
        bt = (Button)findViewById(R.id.btEscolheDiarista);
        bt.setOnClickListener(this);

        ExibeDataListener listener = new ExibeDataListener();
        edt.setOnClickListener(listener);
        edt.setOnFocusChangeListener(listener);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        end = bundle.getString("endereco");
        servs = bundle.getString("servicos");




    }

    private void exibeData(){
        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this,new SelecionaDataListener(),ano,mes,dia);
        dlg.show();
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this,TelaSolicitacoes.class);
        dataServico = edt.getText().toString();
        it.putExtra("endereco",end);
        it.putExtra("servicos",servs);
        it.putExtra("dataservico", dataServico);

        startActivity(it);
    }

    private class ExibeDataListener implements View.OnClickListener,View.OnFocusChangeListener {


        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus) {
                exibeData();
            }
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Date date = DateUtils.getDate(year, monthOfYear, dayOfMonth);
            String dt = DateUtils.dateToString(year, monthOfYear, dayOfMonth);

            edt.setText(dt);


        }
    }
}
