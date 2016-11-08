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
import br.com.diaristaslimpo.limpo.Controler.telas.TelaLogin;

/**
 * Created by user on 24/04/2016.
 */
public class EnviaServicos extends AsyncTask<String, Void, String> { // linha 22
    private Context context;
    private ProgressDialog dialog;
    private ConectaWS requester;
    private DataBase dataBase;
    private SQLiteDatabase conn;


    public EnviaServicos(Context context) {

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
            dataBase = new DataBase(context);
            conn = dataBase.getWritableDatabase();

            ScriptSQL scriptSQL = new ScriptSQL(conn);
            GeraJson geraJson = new GeraJson();
            int idDiarista = scriptSQL.retornaIdDiarista();
            String json = geraJson.jsonEnviaServico(idDiarista,Integer.parseInt(params[0]),Integer.parseInt(params[1]),Integer.parseInt(params[2]));

            requester = new ConectaWS();

            final JSONObject recebe = requester.conexao("http://limpo-dev.sa-east-1.elasticbeanstalk.com/api/DiaristaServico/Cadastrar", json);


            scriptSQL.insereServicos(
                    recebe.getInt("Limpeza"),
                    recebe.getInt("PassarRoupa"),
                    recebe.getInt("LavarRoupa"));

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
            Intent it = new Intent(context, TelaLogin.class);
            context.startActivity(it);

        }else{
            dialog.dismiss();
            onCancelled();
            MessageBox.show(context,"Erro ao efetuar Alteração dos Dados",resposta);

        }





    }


}

