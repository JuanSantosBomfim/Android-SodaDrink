package com.sodadrink.br.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by 15251379 on 22/05/2017.
 */

public class ListaAdapterEnderecos extends ArrayAdapter{

    Context context;
    ArrayList<ClienteJuridico> listaClientesJuridicos;

    TextView txtNomeFantasia,txtTelefone,txtEndereco;

    public ListaAdapterEnderecos(Context context, ArrayList<ClienteJuridico> listaClientesJuridicos) {
        super(context, 0, listaClientesJuridicos);

        this.context = context;
        this.listaClientesJuridicos = listaClientesJuridicos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_enderencos_lista,null);

        txtNomeFantasia = (TextView)view.findViewById(R.id.txtNomeFantasia);
        txtTelefone = (TextView)view.findViewById(R.id.txtTelefone);
        txtEndereco = (TextView)view.findViewById(R.id.txtEndereco);

        txtNomeFantasia.setText(listaClientesJuridicos.get(position).getNomeFantasia());
        txtTelefone.setText("Telefone: "+listaClientesJuridicos.get(position).getTelefone());
        txtEndereco.setText("Endereço: "+listaClientesJuridicos.get(position).getCidade()+" - "+listaClientesJuridicos.get(position).getBairro()+", "+listaClientesJuridicos.get(position).getLogradouro()+" - "+listaClientesJuridicos.get(position).getNumero());

        return view;
    }

}
