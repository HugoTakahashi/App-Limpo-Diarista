package br.com.diaristaslimpo.limpo.Controler.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;
import br.com.diaristaslimpo.limpo.Controler.telas.TelaCadastroEndereco;

/**
 * Created by user on 24/04/2016.
 */
public class EnviaCadastro extends AsyncTask<String, Void, String> { // linha 22
    private Context context;
    private ProgressDialog dialog;
    private ConectaWS requester;
    private DataBase dataBase;
    private SQLiteDatabase conn;


    public EnviaCadastro(Context context) {

        this.context = context;

    }

    @Override
    protected void onPreExecute() {// antes de usar a thread segundaria
      dialog = ProgressDialog.show(context, "Aguarde", "Efetuando Cadastro", true, true);

    }

    @Override
    protected String doInBackground(String... params) {// thread em segundaria
        String resp = "oi";
        try {
            String json = params[0];

            requester = new ConectaWS();

            final JSONObject recebe = requester.conexao("http://limpo-dev.sa-east-1.elasticbeanstalk.com/api/Diarista/Cadastrar", json);

            dataBase = new DataBase(context);
            conn = dataBase.getWritableDatabase();

            ScriptSQL scriptSQL = new ScriptSQL(conn);
            scriptSQL.inserirDiarista(
                    recebe.getString("Id"),
                    recebe.getString("Nome"),
                    recebe.getString("Sobrenome"),
                    recebe.getString("DataNascimento"),//verificar se web service está pronto
                    recebe.getInt("Cpf"),
                    recebe.getString("Email"),
                    Integer.parseInt(recebe.getString("Celular")),
                    recebe.getString("Genero"));
            resp = null;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            resp = "Dados invalidos ou já cadastrados";
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String resposta) { // thread principal

        if (resposta == null) {
            dialog.dismiss();
            Intent it = new Intent(context, TelaCadastroEndereco.class);
            context.startActivity(it);

        }else{
            dialog.dismiss();
            onCancelled();
            MessageBox.show(context,"Erro ao efetuar o cadastro",resposta);

        }





    }


}

