package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amon on 20/05/2017.
 */

public class DetalhesPromocaoFragment extends Fragment{

    String UrlMostrarParticipandoPromocoes = Enderecos.UrlMostrarParticipandoPromocoes();
    String UrlCadastrarClienteNaPromocao = Enderecos.UrlCadastrarClienteNaPromocao();

    int id_promocao;
    int id_cliente;

    int id_promocaoParticipa;

    public DetalhesPromocaoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhes_promocao, container, false);

        Bundle bundle = getArguments();

        id_promocao = bundle.getInt("id_promocao");
        int aprovado = bundle.getInt("aprovado");
        String imagem = bundle.getString("imagem");
        String descricao = bundle.getString("descricao");
        String passo1 = bundle.getString("passo1");
        String passo2 = bundle.getString("passo2");
        String passo3 = bundle.getString("passo3");
        String validade = bundle.getString("validade");
        id_cliente = bundle.getInt("id_cliente");

        ImageView imgPromocao = (ImageView)view.findViewById(R.id.imgPromocaoDetalhes);

        if(imagem != "null" && imagem != "" && !imagem.isEmpty() && imagem != null){

            String caminhoImg = Enderecos.CaminhoImagemNoCMS() + imagem;

            Picasso.with(getContext()).load(caminhoImg).into(imgPromocao);

        }

        TextView txtDescricaoPromocaoDetalhes = (TextView)view.findViewById(R.id.txtDescricaoPromocaoDetalhes);
        txtDescricaoPromocaoDetalhes.setText(descricao);

        TextView txtPasso1PromocaoDetalhes = (TextView)view.findViewById(R.id.txtPasso1PromocaoDetalhes);
        txtPasso1PromocaoDetalhes.setText(passo1);

        TextView txtPasso2PromocaoDetalhes = (TextView)view.findViewById(R.id.txtPasso2PromocaoDetalhes);
        txtPasso2PromocaoDetalhes.setText(passo2);

        TextView txtPasso3PromocaoDetalhes = (TextView)view.findViewById(R.id.txtPasso3PromocaoDetalhes);
        txtPasso3PromocaoDetalhes.setText(passo3);

        Button btnParticiparPromocao = (Button)view.findViewById(R.id.btnParticiparPromocao);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, UrlMostrarParticipandoPromocoes, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("PROMOCAO")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("PROMOCAO");

                        JSONObject objectjFinal = jsonArray.getJSONObject(0);

                        id_promocaoParticipa = objectjFinal.getInt("id_promocao");

                        if(id_promocaoParticipa == id_promocao){

                            btnParticiparPromocao.setText("Você já está participando dessa promocão");

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("id_cliente", ""+id_cliente);
                parametros.put("id_promocao", ""+id_promocao);

                return parametros;

            }
        };
        requestQueue.add(request);

        btnParticiparPromocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnParticiparPromocao.getText().toString().equalsIgnoreCase("Participar")) {

                    //Toast.makeText(getContext(),"id_cliente = "+id_cliente,Toast.LENGTH_SHORT).show();

                    if(id_cliente == 0){

                        Toast.makeText(getContext(),"Faça login para participar de uma Promoção!",Toast.LENGTH_SHORT).show();

                    }else{

                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlCadastrarClienteNaPromocao, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {


                            }

                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> parametros = new HashMap<String, String>();

                                parametros.put("id_cliente_fisico", "" + id_cliente);
                                parametros.put("id_promocao", "" + id_promocao);

                                return parametros;
                            }

                        };

                        requestQueue.add(stringRequest);

                        new AlertDialog.Builder(getContext())
                                .setTitle("Sucesso!")
                                .setMessage("Agora você está participando da Promoção!")
                                .setCancelable(true).show();

                        FragmentManager fragmentManager = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

                    }

                }

            }
        });

        TextView txtValidadePromocaoDetalhes = (TextView)view.findViewById(R.id.txtValidadePromocaoDetalhes);
        txtValidadePromocaoDetalhes.setText("Promoção Disponível Até: "+validade);

        return view;
    }
}
