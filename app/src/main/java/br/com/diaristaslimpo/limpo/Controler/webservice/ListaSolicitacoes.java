package br.com.diaristaslimpo.limpo.Controler.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjListaSolicitacoes;
import br.com.diaristaslimpo.limpo.banco.DataBase;
import br.com.diaristaslimpo.limpo.banco.ScriptSQL;
import br.com.diaristaslimpo.limpo.Model.helper.GeraJson;
import br.com.diaristaslimpo.limpo.Model.helper.MessageBox;

/**
 * Created by user on 24/04/2016.
 */
public class ListaSolicitacoes extends AsyncTask<String, Void, String> { // linha 22
    private Context context;
    private ProgressDialog dialog;
    private ConectaWS requester;
    private DataBase dataBase;
    private SQLiteDatabase conn;


    public ListaSolicitacoes(Context context) {

        this.context = context;

    }

    @Override
    protected void onPreExecute() {// antes de usar a thread segundaria
        dialog = ProgressDialog.show(context, "Aguarde", "Buscando Solicitações", true, true);

    }

    @Override
    protected String doInBackground(String... params) {// thread em segundaria
        String resp = "oi";
        dataBase = new DataBase(context);
        conn = dataBase.getWritableDatabase();
        ScriptSQL scriptSQL = new ScriptSQL(conn);
        scriptSQL.limpaListaSolicitacao();
        ObjListaSolicitacoes obj = new ObjListaSolicitacoes();
        try {

            GeraJson geraJson = new GeraJson();
            requester = new ConectaWS();
            String json2 = geraJson.jsonId(params[0]);

            final JSONArray recebe = requester.conexaoArray("http://limpo-dev.sa-east-1.elasticbeanstalk.com/api/Solicitacao/ListaPorDiarista", json2);
            for(int i=0;i < recebe.length();i++) {
                JSONObject json = recebe.getJSONObject(i);
                obj.setIdCliente((Integer) json.get("IdCliente"));
                obj.setIdSolicitacao((Integer) json.get("IdSolicitacao"));
                obj.setNomeCliente((String) json.get("Nome"));
                obj.setDataDiaria((String) json.get("DataDiaria"));
                obj.setEndereco((String) json.get("Logradouro"));
                obj.setNumero((String) json.get("Numero"));
                obj.setBairro((String) json.get("Bairro"));
                obj.setCidade((String) json.get("Cidade"));
                obj.setCep((String) json.get("Cep"));
                obj.setLimpeza((String) json.get("Limpeza"));
                obj.setPassarRoupa((String) json.get("PassarRoupa"));
                obj.setLavarRoupa((String) json.get("LavarRoupa"));
                obj.setObservacao((String) json.get("Observacao"));

                scriptSQL.inseriSolicitacao(obj);

                resp = null;
            }

        } catch (JSONException e) {
            resp = "Usuario ou senha invalido";
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String resposta) { // thread principal

        if (resposta == null) {
            dialog.dismiss();
            //Intent it = new Intent(context, TelaInicial.class);
            //context.startActivity(it);

        } else {
            dialog.dismiss();
            onCancelled();
            MessageBox.show(context, "Erro ao efetuar Busca", resposta);

        }


    }


}

