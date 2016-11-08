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
import br.com.diaristaslimpo.limpo.Model.helper.GeraJson;
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;
import br.com.diaristaslimpo.limpo.Controler.telas.TelaInicial;

/**
 * Created by user on 24/04/2016.
 */
public class EnviaLogin extends AsyncTask<String, Void, String> { // linha 22
    private Context context;
    private ProgressDialog dialog;
    private ConectaWS requester;
    private String pfv, email, senha;
    private String id, login;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private String ativo;

    public EnviaLogin(Context context) {

        this.context = context;

    }

    @Override
    protected void onPreExecute() {// antes de usar a thread segundaria
        dialog = ProgressDialog.show(context, "Aguarde", "Efetuando Login", true, true);

    }

    @Override
    protected String doInBackground(String... params) {// thread em segundaria
        String resp = "oi";
        try {
            email = params[0];
            senha = params[1];
            GeraJson geraJson = new GeraJson();
            requester = new ConectaWS();
            pfv = geraJson.jsonLogin(email, senha);

            final JSONObject recebe = requester.conexao("http://limpo-dev.sa-east-1.elasticbeanstalk.com/api/Diarista/Login", pfv);

            try {
                if (recebe.getString("Message") != null) {
                    return resp = recebe.getString("Message");
                }
            }catch (JSONException e){

            }

            ativo = recebe.getString("Ativo");
            if (ativo.equals("true")) {

                id = recebe.getString("Id");
                login = recebe.getString("Email");


                dataBase = new DataBase(context);
                conn = dataBase.getWritableDatabase();

                ScriptSQL scriptSQL = new ScriptSQL(conn);
                scriptSQL.inserirLogin(id, login, "1");
                scriptSQL.inserirDiarista(
                        recebe.getString("Id"),
                        recebe.getString("Nome"),
                        recebe.getString("Sobrenome"),
                        recebe.getString("DataNascimento"),
                        recebe.getInt("Cpf"),
                        recebe.getString("Email"),
                        Integer.parseInt(recebe.getString("Celular")),
                        recebe.getString("Genero"));

                resp = null;
            } else {

            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            resp = "Usuario ou senha invalido";
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String resposta) { // thread principal

        if (resposta == null) {
            dialog.dismiss();
            Intent it = new Intent(context, TelaInicial.class);
            context.startActivity(it);

        } else {
            dialog.dismiss();
            onCancelled();
            MessageBox.show(context, "Erro ao efetuar o Login", resposta);

        }


    }


}

