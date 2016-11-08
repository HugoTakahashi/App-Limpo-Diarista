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
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;
import br.com.diaristaslimpo.limpo.Controler.telas.TelaInicial;

/**
 * Created by user on 24/04/2016.
 */
public class EnviaSolicitacao extends AsyncTask<String, Void, String> { // linha 22
    private Context context;
    private ProgressDialog dialog;
    private ConectaWS requester;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private String json;

    public EnviaSolicitacao(Context context) {

        this.context = context;

    }

    @Override
    protected void onPreExecute() {// antes de usar a thread segundaria
        dialog = ProgressDialog.show(context, "Aguarde", "Gravando Solicitação", true, true);

    }

    @Override
    protected String doInBackground(String... params) {// thread em segundaria
        String resp = "oi";
        try {
            json = params[0];
            requester = new ConectaWS();

            final JSONObject recebe = requester.conexao("http://limpo-dev.sa-east-1.elasticbeanstalk.com/api/Solicitacao/Solicitar", json);

            try {
                if (recebe.getString("Message") != null) {
                    return resp = recebe.getString("Message");
                }
            }catch (JSONException e){

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

