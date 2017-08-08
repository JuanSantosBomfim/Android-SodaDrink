package com.sodadrink.br.mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerguntasPesquisaFragment extends Fragment {

    String UrlCadastrarPesquisa = Enderecos.UrlCadastrarPesquisa();

    Button btnSim,btnNao;
    TextView txtNumeroPergunta,txtPergunta;

    Bundle recebeBundle;

    public PerguntasPesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perguntas_pesquisa, container, false);

        btnSim = (Button)view.findViewById(R.id.btnSim);
        btnNao = (Button)view.findViewById(R.id.btnNao);
        txtNumeroPergunta = (TextView)view.findViewById(R.id.txtNumeroPergunta);
        txtPergunta = (TextView)view.findViewById(R.id.txtPergunta);

        recebeBundle = getArguments();
        String numeroPergunta = recebeBundle.getString("numeroPergunta");
        String pergunta = recebeBundle.getString("pergunta");

        txtNumeroPergunta.setText(numeroPergunta);
        txtPergunta.setText(pergunta);

        if(txtNumeroPergunta.getText().toString().contains("1")){

            Bundle bundle = new Bundle();
            bundle.putString("numeroPergunta","2 - Pergunta");
            bundle.putString("pergunta","Você já conhecia nossas marcas?");

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta1",1);

                    chamarTela(bundle);

                }
            });

            btnNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta1",0);

                    chamarTela(bundle);

                }
            });

        }else if(txtNumeroPergunta.getText().toString().contains("2")){

            int resposta1 = recebeBundle.getInt("resposta1");

            Bundle bundle = new Bundle();
            bundle.putString("numeroPergunta","3 - Pergunta");
            bundle.putString("pergunta","Você já conhecia nossa empresa?");
            bundle.putInt("resposta1",resposta1);

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta2",1);

                    chamarTela(bundle);

                }
            });

            btnNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta2",0);

                    chamarTela(bundle);

                }
            });

        }else if(txtNumeroPergunta.getText().toString().contains("3")){

            int resposta1 = recebeBundle.getInt("resposta1");
            int resposta2 = recebeBundle.getInt("resposta2");

            Bundle bundle = new Bundle();
            bundle.putString("numeroPergunta","4 - Pergunta");
            bundle.putString("pergunta","Você já comprou nossas bebidas?");
            bundle.putInt("resposta1",resposta1);
            bundle.putInt("resposta2",resposta2);

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta3",1);

                    chamarTela(bundle);

                }
            });

            btnNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta3",0);

                    chamarTela(bundle);

                }
            });

        }else if(txtNumeroPergunta.getText().toString().contains("4")){

            int resposta1 = recebeBundle.getInt("resposta1");
            int resposta2 = recebeBundle.getInt("resposta2");
            int resposta3 = recebeBundle.getInt("resposta3");

            Bundle bundle = new Bundle();
            bundle.putString("numeroPergunta","5 - Pergunta");
            bundle.putString("pergunta","Você indicaria nossas bebidas para um conhecido?");
            bundle.putInt("resposta1",resposta1);
            bundle.putInt("resposta2",resposta2);
            bundle.putInt("resposta3",resposta3);

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta4",1);

                    chamarTela(bundle);

                }
            });

            btnNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putInt("resposta4",0);

                    chamarTela(bundle);

                }
            });

        }else if(txtNumeroPergunta.getText().toString().contains("5")){

            int resposta1 = recebeBundle.getInt("resposta1");
            int resposta2 = recebeBundle.getInt("resposta2");
            int resposta3 = recebeBundle.getInt("resposta3");
            int resposta4 = recebeBundle.getInt("resposta4");

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getContext(),"1 ="+resposta1+" / 2 ="+resposta2+" / 3 ="+resposta3+" / 4 ="+resposta4, Toast.LENGTH_SHORT).show();

                    int resposta5 = 1;

                    cadastrarPesquisa(resposta1,resposta2,resposta3,resposta4,resposta5);

                }
            });

            btnNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getContext(),"1 ="+resposta1+" / 2 ="+resposta2+" / 3 ="+resposta3+" / 4 ="+resposta4, Toast.LENGTH_SHORT).show();

                    int resposta5 = 0;

                    cadastrarPesquisa(resposta1,resposta2,resposta3,resposta4,resposta5);

                }
            });

        }

        return view;
    }

    public void chamarTela(Bundle bundle){

        FragmentManager fragment = ((PrincipalActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();

        PerguntasPesquisaFragment perguntasPesquisaFragment = new PerguntasPesquisaFragment();
        perguntasPesquisaFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.id_tela_principal, perguntasPesquisaFragment);

        fragmentTransaction.commit();

    }

    public void cadastrarPesquisa(int pergunta1,int pergunta2,int pergunta3,int pergunta4,int pergunta5){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlCadastrarPesquisa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> parametros = new HashMap<>();

                parametros.put("pergunta1", ""+pergunta1);
                parametros.put("pergunta2", ""+pergunta2);
                parametros.put("pergunta3", ""+pergunta3);
                parametros.put("pergunta4", ""+pergunta4);
                parametros.put("pergunta5", ""+pergunta5);

                return parametros;
            }

        };

        requestQueue.add(stringRequest);

        new AlertDialog.Builder(getContext())
                .setTitle("Sucesso!")
                .setMessage("Pesquisa concluida!")
                .setCancelable(true).show();

        FragmentManager fragmentManager = ((PrincipalActivity)getContext()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

    }

}
