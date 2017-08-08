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
 * Created by Amon on 20/05/2017.
 */

public class ListaAdapterPromocao extends ArrayAdapter<Promocao> {

    private Context context;
    private ArrayList<Promocao> listaPromocao;
    Promocao promocaoPosicao;

    public ListaAdapterPromocao(Context context, ArrayList<Promocao> listaPromocao) {

        super(context,0,listaPromocao);

        this.context = context;
        this.listaPromocao = listaPromocao;

    }

    @Override
    public View getView(int posicao, View telaLayoutItemPromocao, ViewGroup parent) {

        promocaoPosicao = this.listaPromocao.get(posicao);

        //*************DEFINIMOS QUAL LAYOUT SERA UTILIZADO NO ADAPTER*********************
        telaLayoutItemPromocao = LayoutInflater.from(this.context).inflate(R.layout.layout_item_promocao_lista, null);

        ImageView imageViewPromocao = (ImageView)telaLayoutItemPromocao.findViewById(R.id.imageViewPromocao);

        if(promocaoPosicao.getImagem() != "null" && promocaoPosicao.getImagem() != "" && !promocaoPosicao.getImagem().isEmpty() && promocaoPosicao.getImagem() != null){

            String imagem = Enderecos.CaminhoImagemNoCMS() + promocaoPosicao.getImagem();

            Picasso.with(getContext()).load(imagem).into(imageViewPromocao);

        }

        TextView txtdescricao = (TextView)telaLayoutItemPromocao.findViewById(R.id.textViewDescricaoPromocao);
        txtdescricao.setText(""+promocaoPosicao.getDescricao());

        Button btnDescricaoPromcocao = (Button)telaLayoutItemPromocao.findViewById(R.id.btnDetalhesPromocao);

        btnDescricaoPromcocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putInt("id_promocao", listaPromocao.get(posicao).getId_promocao());
                bundle.putInt("aprovado", listaPromocao.get(posicao).getAprovado());
                bundle.putString("imagem", listaPromocao.get(posicao).getImagem());
                bundle.putString("descricao", listaPromocao.get(posicao).getDescricao());
                bundle.putString("passo1", listaPromocao.get(posicao).getPasso1());
                bundle.putString("passo2", listaPromocao.get(posicao).getPasso2());
                bundle.putString("passo3", listaPromocao.get(posicao).getPasso3());
                bundle.putString("validade", listaPromocao.get(posicao).getValidade());
                bundle.putInt("id_cliente", listaPromocao.get(posicao).getId_cliente());

                FragmentManager fragmentManager = ((PrincipalActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                DetalhesPromocaoFragment detalhesPromocaoFragment = new DetalhesPromocaoFragment();

                detalhesPromocaoFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.id_tela_principal, detalhesPromocaoFragment);
                fragmentTransaction.commit();

            }
        });

        return telaLayoutItemPromocao;
    }
}
