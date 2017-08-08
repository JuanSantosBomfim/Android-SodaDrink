package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    String UrlBuscarProdutos = Enderecos.UrlBuscarProdutos();
    String UrlMostrarProduto = Enderecos.UrlMostrarProduto();

    EditText edtNomeProdutoBuscar;
    Button btnBuscarHome;

    ListView listView;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    View view;

    ArrayList<Produto> listaProduto;
    ArrayList<Produto> listaProdutoBuscado;

    public HomeFragment(){}

    Timer timer = new Timer();

    @Override
    public void onPause() {
        super.onPause();

        timer.cancel();

        Log.d("Pause", "pause");
    }


    @Override
    public void onResume() {
        super.onResume();
        //Comenta pra não dar erro
        timer.scheduleAtFixedRate(new tempoMudarSlider(), 2000, 4000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view =  inflater.inflate(R.layout.fragment_home, container, false);

        edtNomeProdutoBuscar = (EditText)view.findViewById(R.id.edtNomeProdutoBuscar);
        btnBuscarHome = (Button)view.findViewById(R.id.btnBuscarHome);

        //***********CRIANDO SLIDER************

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerPropagandas);

        viewPagerAdapter = new ViewPagerAdapter(view.getContext());

        viewPager.setAdapter(viewPagerAdapter);

        //**************PEGANDO DADOS MYSQL DA API E CRIANDO A LISTA DE PRODUTOS***********

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        listaProduto = new ArrayList<Produto>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlMostrarProduto, new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("PRODUTOS");

                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject dadosProduto = jsonArray.getJSONObject(i);

                                int id_produto = dadosProduto.getInt("id_produto");
                                int id_categoria = dadosProduto.getInt("id_categoria");
                                int id_fornecedor = dadosProduto.getInt("id_fornecedor");
                                int id_marca = dadosProduto.getInt("id_marca");
                                String nome = dadosProduto.getString("nome");
                                float valorCompra = dadosProduto.getInt("valorCompra");
                                //IMAGEM COM PICASSO
                                String imagem = dadosProduto.getString("imagem");
                                float peso = dadosProduto.getInt("peso");
                                int codBarra = dadosProduto.getInt("codBarra");
                                int porcDesconto = dadosProduto.getInt("porcDesconto");
                                int aprovado = dadosProduto.getInt("aprovado");
                                float valorVenda = dadosProduto.getInt("valorVenda");
                                int quantidadeEstoque = dadosProduto.getInt("quantidadeEstoque");
                                int quantidadeEngradado = dadosProduto.getInt("quantidadeEngradado");
                                int qtdParaOSite = dadosProduto.getInt("qtdParaOSite");

                                String nomeCategoria = dadosProduto.getString("nomeCategoria");
                                String nomeMarca = dadosProduto.getString("nomeMarca");

                                if(imagem != "null" && imagem != "" && !imagem.isEmpty() && imagem != null){

                                    listaProduto.add(new Produto(id_produto,id_categoria,id_fornecedor,id_marca,
                                            nome,valorCompra,imagem,peso,codBarra,porcDesconto,
                                            aprovado,valorVenda,quantidadeEstoque,quantidadeEngradado,qtdParaOSite,
                                            nomeCategoria,nomeMarca));

                                }else{

                                }

                                //*********CRIANDO O ADAPTER NA LISTVIEW DA HOME***********

                                ListaAdapterProduto listaAdapterProduto = new ListaAdapterProduto(getActivity(),listaProduto);

                                listView = (ListView)view.findViewById(R.id.listViewHome);

                                listView.setAdapter(listaAdapterProduto);

                                //***********DEFINE O TAMANHO DA LIST VIEW DE ACORDO COM A QUANTIDADE DE ITENS********
                                Utils utils = new Utils();
                                utils.setListViewHeightBasedOnChildren(listView);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("VOLLEY","ERROR");

            }

        });

        requestQueue.add(jsonObjectRequest);

        btnBuscarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto_digitado = edtNomeProdutoBuscar.getText().toString();
                listView.setAdapter(null);

                listaProdutoBuscado = new ArrayList<Produto>();
                RequestQueue requestQueueBusca = Volley.newRequestQueue(getContext());
                StringRequest requestBusca = new StringRequest(Request.Method.POST, UrlBuscarProdutos, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("PRODUTOS");

                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject dadosProduto = jsonArray.getJSONObject(i);

                                int id_produto = dadosProduto.getInt("id_produto");
                                int id_categoria = dadosProduto.getInt("id_categoria");
                                int id_fornecedor = dadosProduto.getInt("id_fornecedor");
                                int id_marca = dadosProduto.getInt("id_marca");
                                String nome = dadosProduto.getString("nome");
                                float valorCompra = dadosProduto.getInt("valorCompra");
                                //IMAGEM COM PICASSO
                                String imagem = dadosProduto.getString("imagem");
                                float peso = dadosProduto.getInt("peso");
                                int codBarra = dadosProduto.getInt("codBarra");
                                int porcDesconto = dadosProduto.getInt("porcDesconto");
                                int aprovado = dadosProduto.getInt("aprovado");
                                float valorVenda = dadosProduto.getInt("valorVenda");
                                int quantidadeEstoque = dadosProduto.getInt("quantidadeEstoque");
                                int quantidadeEngradado = dadosProduto.getInt("quantidadeEngradado");
                                int qtdParaOSite = dadosProduto.getInt("qtdParaOSite");

                                String nomeCategoria = dadosProduto.getString("nomeCategoria");
                                String nomeMarca = dadosProduto.getString("nomeMarca");

                                if(imagem != "null" && imagem != "" && !imagem.isEmpty() && imagem != null){

                                    listaProdutoBuscado.add(new Produto(id_produto,id_categoria,id_fornecedor,id_marca,
                                            nome,valorCompra,imagem,peso,codBarra,porcDesconto,
                                            aprovado,valorVenda,quantidadeEstoque,quantidadeEngradado,qtdParaOSite,
                                            nomeCategoria,nomeMarca));

                                }else{

                                }

                                //*********CRIANDO O ADAPTER NA LISTVIEW DA HOME***********

                                ListaAdapterProduto listaAdapterProduto = new ListaAdapterProduto(getActivity(),listaProdutoBuscado);

                                listView = (ListView)view.findViewById(R.id.listViewHome);

                                listView.setAdapter(listaAdapterProduto);

                                //***********DEFINE O TAMANHO DA LIST VIEW DE ACORDO COM A QUANTIDADE DE ITENS********
                                Utils utils = new Utils();
                                utils.setListViewHeightBasedOnChildren(listView);

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

                        parametros.put("textoDigitado", texto_digitado);

                        return parametros;

                    }
                };
                requestQueueBusca.add(requestBusca);

            }
        });

        return view;

    }

    public class tempoMudarSlider extends TimerTask {

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }else if(viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);
                    }else{
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
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
