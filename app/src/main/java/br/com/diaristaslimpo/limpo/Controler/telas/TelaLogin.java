package br.com.diaristaslimpo.limpo.Controler.telas;

import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.diaristaslimpo.limpo.Controler.webservice.EnviaLogin;
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;
import br.com.diaristaslimpo.limpo.R;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;

public class TelaLogin extends AppCompatActivity {
    private Button btLogin, btCadastrar;
    private EditText edtEmail, edtSenha;
    private Resources resources;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private String id;
    private int isLogon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        InitViews();

    }

    private void InitViews(){
        resources = getResources();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
          public void afterTextChanged(Editable s) {
                callClearErrors(s);
            }
        };

        edtEmail = (EditText) findViewById(R.id.edtEmailLogin);
        edtEmail.addTextChangedListener(textWatcher);
        edtSenha = (EditText) findViewById(R.id.edtSenhaLogin);
        edtSenha.addTextChangedListener(textWatcher);

        btLogin = (Button) findViewById(R.id.btLogar);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent it = new Intent(TelaLogin.this,TelaInicial.class);
                startActivity(it);
                        new EnviaLogin(TelaLogin.this).execute(edtEmail.getText().toString(), edtSenha.getText().toString());//chamada da classe asynctask


            }
        });
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TelaLogin.this,TelaCadastro1.class);
                startActivity(it);
            }
        });
    }

    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(edtEmail);
        }
    }

    private void clearErrorFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }



    private boolean validateFields() {
        String user = edtEmail.getText().toString().trim();
        String pass = edtSenha.getText().toString().trim();
        return (!isEmptyFields(user, pass));
    }

    private boolean isEmptyFields(String user, String pass) {

        int contaErro = 0;

        if (TextUtils.isEmpty(user)) {
            edtEmail.requestFocus(); //seta o foco para o campo user
            edtEmail.setError(resources.getString(R.string.login_user_required));
            contaErro++;
        }
        else{
            Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
            Matcher m = p.matcher(pass);
            if (!m.find()){

                edtEmail.requestFocus();
                edtEmail.setError(resources.getString(R.string.login_email_invalido));
                contaErro++;
            }
        }

        if (TextUtils.isEmpty(pass)) {
            edtSenha.requestFocus(); //seta o foco para o campo password
            edtSenha.setError(resources.getString(R.string.login_password_required));
            contaErro++;
        }

        if(contaErro > 0)
            return true;
           else
            return false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);
        try {

            isLogon = scriptSQL.isLogin(this);

            if (isLogon == 1) {
                Intent it = new Intent(this, TelaInicial.class);
                startActivity(it);
                finish();
            }
        }catch (SQLException ex){
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());

        }
    }
}
