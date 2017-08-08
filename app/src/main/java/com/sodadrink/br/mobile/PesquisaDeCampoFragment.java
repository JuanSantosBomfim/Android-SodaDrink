package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaDeCampoFragment extends Fragment {

    public PesquisaDeCampoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pesquisa_de_campo, container, false);

        Button btnIniciarPesquisa = (Button)view.findViewById(R.id.btnIniciarPesquisa);

        btnIniciarPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("numeroPergunta","1 - Pergunta");
                bundle.putString("pergunta","Você já conhecia nossas bebidas?");

                FragmentManager fragment = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragment.beginTransaction();

                PerguntasPesquisaFragment perguntasPesquisaFragment = new PerguntasPesquisaFragment();
                perguntasPesquisaFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.id_tela_principal, perguntasPesquisaFragment);

                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
