package com.sodadrink.br.mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreEmpresaFragment extends Fragment {

    String UrlMostrarSobre = Enderecos.UrlMostrarSobre();

    ImageView imageView;
    TextView txtMissao,txtVisao,txtParagrafo;

    public SobreEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sobre_empresa, container, false);

        imageView = (ImageView)view.findViewById(R.id.imageViewLogoSobre);

        txtMissao = (TextView)view.findViewById(R.id.txtMissao);
        txtVisao = (TextView)view.findViewById(R.id.txtVisao);
        txtParagrafo = (TextView)view.findViewById(R.id.txtParagrafo);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlMostrarSobre, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("SOBRE");

                    for(int i=0; i < jsonArray.length(); i++){

                        JSONObject dadosSobre = jsonArray.getJSONObject(i);

                        String id_sobre = dadosSobre.getString("id_sobre");
                        String missao = dadosSobre.getString("missao");
                        String visao = dadosSobre.getString("visao");
                        String paragrafo = dadosSobre.getString("paragrafo");
                        String imagem = dadosSobre.getString("imagem");

                        if(imagem != null && !imagem.isEmpty()){

                            String caminhoImagem = Enderecos.CaminhoImagemNoSodaDrink() + imagem;

                            Picasso.with(getContext()).load(caminhoImagem).into(imageView);

                        }

                        txtMissao.setText(missao);
                        txtVisao.setText(visao);
                        txtParagrafo.setText(paragrafo);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue.add(jsonObjectRequest);

        return view;
    }

}
