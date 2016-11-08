package br.com.diaristaslimpo.limpo.Controler.telas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.Date;

import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.Model.helper.DateUtils;
import br.com.diaristaslimpo.limpo.Model.helper.GeraJson;
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;
import br.com.diaristaslimpo.limpo.Controler.webservice.EnviaCadastro;

public class TelaCadastro1 extends AppCompatActivity {
    private EditText nome,sobrenome,datanasc,cpf,email,senha1,senha2,celular,telefone;
    private String genero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro1);

        nome = (EditText)findViewById(R.id.cadastro_nome);
        sobrenome = (EditText)findViewById(R.id.cadastro_sobrenome);
        datanasc = (EditText)findViewById(R.id.cadastro_nascimento);
        cpf = (EditText)findViewById(R.id.cadastro_cpf);
        email = (EditText)findViewById(R.id.cadastro_email);
        senha1 = (EditText)findViewById(R.id.cadastro_senha1);
        senha2 = (EditText)findViewById(R.id.cadastro_senha2);
        celular = (EditText)findViewById(R.id.cadastro_celular);

        ExibeDataListener listener = new ExibeDataListener();
        datanasc.setOnClickListener(listener);
        datanasc.setOnFocusChangeListener(listener);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.M_radioButton:
                if (checked)
                    genero = "M";
                    break;
            case R.id.F_radioButton:
                if (checked)
                    genero = "F";
                    break;
        }
    }

    private void exibeData(){
        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this,new SelecionaDataListener(),ano,mes,dia);
        dlg.show();
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

            datanasc.setText(dt);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salva_cadastro1,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_enviar_cadastro:

                if (!(senha1.getText().toString().equals(senha2.getText().toString()))){
                    MessageBox.showAlert(this,"Senha não Confere","verificar se as senhas informadas são iguais");
                    break;
                }
                GeraJson geraJson = new GeraJson();
                String json = geraJson.jsonCadastro1(   nome.getText().toString(),
                                                        sobrenome.getText().toString(),
                                                        datanasc.getText().toString(),
                                                        cpf.getText().toString(),
                                                        email.getText().toString(),
                                                        senha1.getText().toString(),
                                                        celular.getText().toString(),
                                                        genero);
                new EnviaCadastro(this).execute(json);//chamada da classe asynctask



        }

        return super.onOptionsItemSelected(item);
    }
}
