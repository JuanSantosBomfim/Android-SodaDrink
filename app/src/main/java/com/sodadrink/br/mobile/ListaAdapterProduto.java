package com.sodadrink.br.mobile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Amongsk on 10/05/2017.
 */

public class ListaAdapterProduto extends ArrayAdapter<Produto>{

    private Context context;
    private ArrayList<Produto> listaProdutos;
    Produto produtoPosicao;

    public ListaAdapterProduto(Context context, ArrayList<Produto> listaProdutos){

        super(context,0,listaProdutos);

        this.context = context;
        this.listaProdutos = listaProdutos;

    }

    @Override
    public View getView(int posicao, View telaLayoutItemProduto, ViewGroup parent) {

        produtoPosicao = this.listaProdutos.get(posicao);

        //**********CRIAMOS O layout_item_produto_lista E DEFINIMOS O LAYOUT QUE SERA USADO*********
        telaLayoutItemProduto = LayoutInflater.from(this.context).inflate(R.layout.layout_item_produto_lista,null);

        ImageView imageView = (ImageView)telaLayoutItemProduto.findViewById(R.id.imageViewProduto);

        if(produtoPosicao.getImagem() != null && !produtoPosicao.getImagem().isEmpty() && produtoPosicao.getImagem() != "null" && produtoPosicao.getImagem() != "") {

            String caminhoImg = Enderecos.CaminhoImagemNoCMS() + produtoPosicao.getImagem();

            Picasso.with(getContext()).load(caminhoImg).into(imageView);

        }

        TextView txtProduto = (TextView)telaLayoutItemProduto.findViewById(R.id.textViewNomeProduto);
        txtProduto.setText(""+produtoPosicao.getNome());

        TextView txtCategoria = (TextView)telaLayoutItemProduto.findViewById(R.id.textViewCategoriaProduto);
        txtCategoria.setText(""+produtoPosicao.getNomeCategoria());

        Button btnDetalhes = (Button)telaLayoutItemProduto.findViewById(R.id.btnDetalhes);
        //btnDetalhes.setText(""+produtoPosicao.getId_produto());

        btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //listaProdutos.get(posicao).getNome();
                //Toast.makeText(getContext(),listaProdutos.get(posicao).getNome(), Toast.LENGTH_SHORT).show();

                //************CRIA UM OBJETO PARA ENVIAR PARA A TELA DE DETALHES**************
                Bundle bundle = new Bundle();
                bundle.putString("imagem",listaProdutos.get(posicao).getImagem());
                bundle.putString("nomeProduto", listaProdutos.get(posicao).getNome());
                bundle.putString("nomeCategoria", listaProdutos.get(posicao).getNomeCategoria());
                bundle.putString("nomeMarca", listaProdutos.get(posicao).getNomeMarca());

                FragmentManager fragmentManager = ((PrincipalActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                DetalhesBebidaFragment detalhesBebidaFragment = new DetalhesBebidaFragment();

                //**************DEFINE O OBJETO QUE SERÁ ENVIADO************
                detalhesBebidaFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.id_tela_principal, detalhesBebidaFragment);
                fragmentTransaction.commit();
            }
        });

        return telaLayoutItemProduto;

    }
}
