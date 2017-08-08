package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalhesBebidaFragment extends Fragment {

    View view;

    public DetalhesBebidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detalhes_bebida, container, false);

        //********PEGA OS VALORES ENVIADOS AO CLICAR EM DETALHES DA BEBIDA************
        Bundle bundle = getArguments();

        String imagem = bundle.getString("imagem");
        String nomeProduto = bundle.getString("nomeProduto");
        String nomeCategoria = bundle.getString("nomeCategoria");
        String nomeMarca = bundle.getString("nomeMarca");

        //*********DEFINE O VALOR OBTIDO EM UM TEXT VIEW*************

        ImageView imageView = (ImageView)view.findViewById(R.id.imageViewProdutoDetalhes);

        if(imagem != null && !imagem.isEmpty()) {

            String caminhoImg = Enderecos.CaminhoImagemNoCMS() + imagem;

            Picasso.with(getContext()).load(caminhoImg).into(imageView);

        }

        TextView txtNomeBebida = (TextView)view.findViewById(R.id.txtNomeBebida);
        txtNomeBebida.setText(nomeProduto);

        TextView txtCategoria = (TextView)view.findViewById(R.id.txtCategoria);
        txtCategoria.setText(nomeCategoria);

        TextView txtMarca = (TextView)view.findViewById(R.id.txtMarca);
        txtMarca.setText(nomeMarca);

        //Toast.makeText(getContext(),""+imagem,Toast.LENGTH_SHORT).show();

        return view;
    }

}
