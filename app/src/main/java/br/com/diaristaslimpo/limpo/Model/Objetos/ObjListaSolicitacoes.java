package br.com.diaristaslimpo.limpo.Model.Objetos;

/**
 * Created by user on 18/09/2016.
 */
public class ObjListaSolicitacoes {

    private int idCliente;
    private int idSolicitacao;
    private String NomeCliente;
    private String dataDiaria;
    private String Endereco;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String cep;
    private String Limpeza;
    private String PassarRoupa;
    private String LavarRoupa;
    private String Observacao;

    public ObjListaSolicitacoes(){}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        NomeCliente = nomeCliente;
    }

    public String getDataDiaria() {
        return dataDiaria;
    }

    public void setDataDiaria(String dataDiaria) {
        this.dataDiaria = dataDiaria;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLimpeza() {
        return Limpeza;
    }

    public void setLimpeza(String limpeza) {
        Limpeza = limpeza;
    }

    public String getPassarRoupa() {
        return PassarRoupa;
    }

    public void setPassarRoupa(String passarRoupa) {
        PassarRoupa = passarRoupa;
    }

    public String getLavarRoupa() {
        return LavarRoupa;
    }

    public void setLavarRoupa(String lavarRoupa) {
        LavarRoupa = lavarRoupa;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }
}
