package br.com.diaristaslimpo.limpo.banco;

/**
 * Created by Adelson on 05/08/2015.
 */
public class Tabelas {

    public static String getCreateLogin(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists login( ");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("idUsuario integer,");
        sqlBuilder.append("Login TEXT,");
        sqlBuilder.append("logado integer ");
        //sqlBuilder.append("tipotelefone varchar(1), ");
       //sqlBuilder.append("email varchar(255), ");
       // sqlBuilder.append("tipoemail varchar(1), ");
       // sqlBuilder.append("endereco varchar(255), ");
       // sqlBuilder.append("tipoendereco varchar(1), ");
        //sqlBuilder.append("datalogon date, ");
        //sqlBuilder.append("tipodatasespeciais varchar(1), ");
        //sqlBuilder.append("grupos varchar(255) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateDiarista() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists diarista(");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("IdDiarista integer,");
        sqlBuilder.append("Nome TEXT,");
        sqlBuilder.append("Sobrenome TEXT,");
        sqlBuilder.append("DataNascimento TEXT,");
        sqlBuilder.append("Cpf INTEGER,");
        sqlBuilder.append("Email TEXT,");
        sqlBuilder.append("Celular INTEGER,");
        sqlBuilder.append("Genero TEXT");

        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateEndereco(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists endereco(");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("IdDiarista integer,");
        sqlBuilder.append("IdEndereco integer,");
        sqlBuilder.append("IdentificacaoEndereco TEXT,");
        sqlBuilder.append("Cep integer,");
        sqlBuilder.append("Logradouro TEXT,");
        sqlBuilder.append("Numero integer,");
        sqlBuilder.append("Complemento TEXT,");
        sqlBuilder.append("Bairro TEXT,");
        sqlBuilder.append("Cidade TEXT,");
        sqlBuilder.append("Pontoreferencia TEXT,");
        sqlBuilder.append("Latitude TEXT,");
        sqlBuilder.append("Longitude TEXT");
        sqlBuilder.append(");");

        return sqlBuilder.toString();

    }

    public static String getCreateListaSolicitacao() {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists listaSolicitacao(");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("IdCliente integer,");
        sqlBuilder.append("IdSolicitacao integer,");
        sqlBuilder.append("NomeCliente TEXT,");
        sqlBuilder.append("DataDiaria TEXT,");
        sqlBuilder.append("Endereco TEXT,");
        sqlBuilder.append("Numero TEXT,");
        sqlBuilder.append("Bairro TEXT,");
        sqlBuilder.append("Cidade TEXT,");
        sqlBuilder.append("Cep TEXT,");
        sqlBuilder.append("Limpeza TEXT,");
        sqlBuilder.append("PassarRoupa TEXT,");
        sqlBuilder.append("LavarRoupa TEXT,");
        sqlBuilder.append("Observacao TEXT");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateHistorico(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists historico(");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("IdSolicitacao integer,");
        sqlBuilder.append("nome TEXT,");
        sqlBuilder.append("valor integer,");
        sqlBuilder.append("status TEXT,");
        sqlBuilder.append("dataSolicitacao TEXT");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateServico(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("create table if not exists servico(");
        sqlBuilder.append("_id integer not null primary key autoincrement, ");
        sqlBuilder.append("Limpeza Text,");
        sqlBuilder.append("Passarroupa TEXT,");
        sqlBuilder.append("LavarRoupa Text");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
