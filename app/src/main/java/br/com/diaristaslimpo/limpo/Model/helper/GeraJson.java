package br.com.diaristaslimpo.limpo.Model.helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 24/04/2016.
 */
public class GeraJson {

    public String jsonLogin(String usuario, String senha) {
        JSONObject json = new JSONObject();
        try {

            json.put("Email", usuario);
            json.put("Senha", senha);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);
    }

    public String jsonCadastro1(String nome, String sobrenome, String datanasc, String cpf, String email, String senha, String celular, String genero) {
        JSONObject json = new JSONObject();

        try {
            json.put("Nome", nome);
            json.put("Sobrenome", sobrenome);
            json.put("DataNascimentoFormatada", datanasc);
            json.put("Cpf", cpf);
            json.put("Email", email);
            json.put("Senha", senha);
            json.put("Celular", celular);
            json.put("Genero", genero);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonAlteraCadastro(int id, String nome, String sobrenome, String datanasc, String cpf, String email, String celular) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdCliente",id);
            json.put("Nome", nome);
            json.put("Sobrenome", sobrenome);
            json.put("DataNasc", datanasc);
            json.put("CPF", cpf);
            json.put("Email", email);
            json.put("Celular", celular);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonCadastraEndereço(int IdDiarista, String idEndereco, String nomeEndereco, String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String pontoReferencia) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdDiarista",IdDiarista);
            json.put("IdEndereco", idEndereco);
            json.put("IdentificacaoEndereco", nomeEndereco);
            json.put("Cep", cep);
            json.put("Logradouro", logradouro);
            json.put("Numero", numero);
            json.put("Complemento", complemento);
            json.put("Bairro", bairro);
            json.put("Cidade", cidade);
            json.put("PontoReferencia", pontoReferencia);



        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonBuscaDiarista(String id, String data, int limpeza, int passarroupa, int lavarroupa) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdCliente",id);
            json.put("DataServico",data);
            json.put("Limpeza",limpeza);
            json.put("PassarRoupa",passarroupa);
            json.put("LavarRoupa",lavarroupa);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonEnviaSolicitacao(String idCliente, int idDiarista, String idEndereco, int limpeza, int passarroupa, int lavarroupa, String dataServico) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdCliente",idCliente);
            json.put("DataServico",dataServico);
            json.put("Limpeza",limpeza);
            json.put("PassaRoupa",passarroupa);
            json.put("LavaRoupa",lavarroupa);
            json.put("IdDiarista",idDiarista);
            json.put("IdEnderecoCliente",idEndereco);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonId(String idDiarista) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdDiarista",idDiarista);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);

    }

    public String jsonEnviaServico(int idDiarista, int param, int param1, int param2) {
        JSONObject json = new JSONObject();

        try {
            json.put("IdDiarista",idDiarista);
            json.put("Limpeza",param);
            json.put("PassarRoupa",param1);
            json.put("LavarRoupa",param2);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return String.valueOf(json);
    }


}
