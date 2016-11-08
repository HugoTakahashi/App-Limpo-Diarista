package br.com.diaristaslimpo.limpo.Model.helper;

/**
 * Created by user on 23/08/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.diaristaslimpo.limpo.Model.Objetos.ObjListaSolicitacoes;
import br.com.diaristaslimpo.limpo.R;

public class AdapterListaSolicitacoes extends BaseAdapter{
    private LayoutInflater mInflater;
    private ArrayList<ObjListaSolicitacoes> itens;

    public AdapterListaSolicitacoes(Context context, ArrayList<ObjListaSolicitacoes> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount()
    {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public ObjListaSolicitacoes getItem(int position)
    {
        return itens.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        ObjListaSolicitacoes item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.listview_lista_solicitacoes, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.lstSoliNome)).setText(item.getNomeCliente());
        ((TextView) view.findViewById(R.id.lstSoliData)).setText(String.valueOf(item.getDataDiaria()));
        ((TextView) view.findViewById(R.id.lstSoliEnd)).setText(item.getBairro()+", "+item.getCidade());

        return view;
    }
}
