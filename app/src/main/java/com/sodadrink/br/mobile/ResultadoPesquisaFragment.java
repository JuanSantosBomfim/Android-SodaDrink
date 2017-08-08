package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadoPesquisaFragment extends Fragment {

    String UrlMostrarTodosResultadosPesquisa = Enderecos.UrlMostrarTodosResultadosPesquisa();
    String UrlMostrarResultadoPesquisa = Enderecos.UrlMostrarResultadoPesquisa();

    int totalResutltados = 0;

    TextView txtSimResposta1,txtSimResposta2,txtSimResposta3,txtSimResposta4,txtSimResposta5,txtNaoResposta1,txtNaoResposta2,txtNaoResposta3,txtNaoResposta4,txtNaoResposta5;

    public ResultadoPesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultado_pesquisa, container, false);

        txtSimResposta1 = (TextView)view.findViewById(R.id.txtSimResposta1);
        txtSimResposta2 = (TextView)view.findViewById(R.id.txtSimResposta2);
        txtSimResposta3 = (TextView)view.findViewById(R.id.txtSimResposta3);
        txtSimResposta4 = (TextView)view.findViewById(R.id.txtSimResposta4);
        txtSimResposta5 = (TextView)view.findViewById(R.id.txtSimResposta5);

        txtNaoResposta1 = (TextView)view.findViewById(R.id.txtNaoResposta1);
        txtNaoResposta2 = (TextView)view.findViewById(R.id.txtNaoResposta2);
        txtNaoResposta3 = (TextView)view.findViewById(R.id.txtNaoResposta3);
        txtNaoResposta4 = (TextView)view.findViewById(R.id.txtNaoResposta4);
        txtNaoResposta5 = (TextView)view.findViewById(R.id.txtNaoResposta5);

        RequestQueue requestQueueTotal = Volley.newRequestQueue(getContext());
        StringRequest stringRequestTotal = new StringRequest(Request.Method.POST, UrlMostrarTodosResultadosPesquisa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("RESULTADO")){

                        JSONArray jsonArray = jsonObject.getJSONArray("RESULTADO");
                        totalResutltados = jsonArray.length();

                        mostrarResultados();

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
        requestQueueTotal.add(stringRequestTotal);

        return view;
    }

    public void mostrarResultados(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlMostrarResultadoPesquisa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("RESULTADO")){

                        JSONArray jsonArray = jsonObject.getJSONArray("RESULTADO");

                        for(int i = 0; i < jsonArray.length(); i++){

                            JSONObject objectjFinal = jsonArray.getJSONObject(i);

                            int totalPergunta1 = objectjFinal.getInt("totalPergunta1");
                            int totalPergunta2 = objectjFinal.getInt("totalPergunta2");
                            int totalPergunta3 = objectjFinal.getInt("totalPergunta3");
                            int totalPergunta4 = objectjFinal.getInt("totalPergunta4");
                            int totalPergunta5 = objectjFinal.getInt("totalPergunta5");

                            txtSimResposta1.setText(""+totalPergunta1);
                            txtSimResposta2.setText(""+totalPergunta2);
                            txtSimResposta3.setText(""+totalPergunta3);
                            txtSimResposta4.setText(""+totalPergunta4);
                            txtSimResposta5.setText(""+totalPergunta5);

                            int totalNaoResposta1 = totalResutltados - totalPergunta1;
                            txtNaoResposta1.setText(""+totalNaoResposta1);

                            int totalNaoResposta2 = totalResutltados - totalPergunta2;
                            txtNaoResposta2.setText(""+totalNaoResposta2);

                            int totalNaoResposta3 = totalResutltados - totalPergunta3;
                            txtNaoResposta3.setText(""+totalNaoResposta3);

                            int totalNaoResposta4 = totalResutltados - totalPergunta4;
                            txtNaoResposta4.setText(""+totalNaoResposta4);

                            int totalNaoResposta5 = totalResutltados - totalPergunta5;
                            txtNaoResposta5.setText(""+totalNaoResposta5);


                        }

                        //Toast.makeText(getContext(),"ARRAY = "+ totalResutltados,Toast.LENGTH_SHORT).show();

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

        requestQueue.add(stringRequest);

    }

}
