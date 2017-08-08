package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PromocoesFragment extends Fragment {

    String UrlMostrarPromocao = Enderecos.UrlMostrarPromocao();

    ListView listViewPromocao;

    int id_cliente;

    public PromocoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_promocoes, container, false);

        listViewPromocao = (ListView)view.findViewById(R.id.listViewPromocao);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        ArrayList<Promocao> listPromocao = new ArrayList<Promocao>();

        Bundle bundle = getArguments();
        id_cliente = bundle.getInt("id_cliente");

        StringRequest request = new StringRequest(Request.Method.POST, UrlMostrarPromocao, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("PROMOCAO")){

                        JSONArray jsonArray = jsonObject.getJSONArray("PROMOCAO");

                        for(int i = 0; i < jsonArray.length(); i++){

                            JSONObject objectjFinal = jsonArray.getJSONObject(i);

                            int id_promocao = objectjFinal.getInt("id_promocao");
                            String descricao = objectjFinal.getString("descricao");
                            int aprovado = objectjFinal.getInt("aprovado");
                            String imagem = objectjFinal.getString("imagem");
                            String validade = objectjFinal.getString("validade");
                            String passo1 = objectjFinal.getString("passo1");
                            String passo2 = objectjFinal.getString("passo2");
                            String passo3 = objectjFinal.getString("passo3");

                            if(imagem != "null" && imagem != "" && !imagem.isEmpty() && imagem != null){

                                listPromocao.add(new Promocao(id_promocao,descricao,aprovado,imagem,validade,passo1,passo2,passo3,id_cliente));

                            }else{

                            }

                            ListaAdapterPromocao listaAdapterPromocao = new ListaAdapterPromocao(getContext(), listPromocao);

                            ListView listViewPromocao = (ListView)view.findViewById(R.id.listViewPromocao);
                            listViewPromocao.setAdapter(listaAdapterPromocao);

                            //***********DEFINE O TAMANHO DA LIST VIEW DE ACORDO COM A QUANTIDADE DE ITENS********
                            Utils utils = new Utils();
                            utils.setListViewHeightBasedOnChildren(listViewPromocao);


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

                return parametros;

            }
        };
        requestQueue.add(request);

        return view;
    }

    //METODO PARA DEFINIR O TAMANHO DA LIST VIEW
    public class Utils {

        public void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

    }

}
