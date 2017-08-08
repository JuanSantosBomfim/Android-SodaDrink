package com.sodadrink.br.mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LojasFragment extends Fragment{

    View mView;

    Button btnMostraMapa;
    ListView listViewLojasProximas;

    String UrlMostrarEnderecosClientes = Enderecos.UrlMostrarEnderecosClientes();

    ArrayList<ClienteJuridico> listaCliente;
    ArrayList<String> listaEnderecos;
    ArrayList<String> listaNomes;

    public LojasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_lojas, container, false);

        btnMostraMapa = (Button)mView.findViewById(R.id.btnMostraMapa);
        listViewLojasProximas = (ListView)mView.findViewById(R.id.listViewLojasProximas);

        listaCliente = new ArrayList<>();
        listaNomes = new ArrayList<>();
        listaEnderecos = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlMostrarEnderecosClientes, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("ENDERECOS");

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id_cliente = jsonObject.getInt("id_cliente");
                        int id_estado = jsonObject.getInt("id_estado");
                        String razaoSocial = jsonObject.getString("razaoSocial");
                        String nomeFantasia = jsonObject.getString("nomeFantasia");
                        String inscricaoEstadual = jsonObject.getString("inscricaoEstadual");
                        String cnpj = jsonObject.getString("cnpj");
                        String dtCadastro = jsonObject.getString("dtCadastro");
                        String login = jsonObject.getString("login");
                        String senha = jsonObject.getString("senha");
                        String email = jsonObject.getString("email");
                        String telefone = jsonObject.getString("telefone");
                        String cep = jsonObject.getString("cep");
                        String cidade = jsonObject.getString("cidade");
                        String bairro = jsonObject.getString("bairro");
                        String logradouro = jsonObject.getString("logradouro");
                        int numero = jsonObject.getInt("numero");
                        String imagem = jsonObject.getString("imagem");

                        listaNomes.add(nomeFantasia);
                        listaEnderecos.add(cidade+" "+bairro+" "+logradouro);

                        listaCliente.add(new ClienteJuridico(id_cliente,id_estado,razaoSocial,nomeFantasia,inscricaoEstadual,cnpj,dtCadastro,login,senha,email,telefone,cep,cidade,bairro,logradouro,numero,imagem));

                    }

                    ListaAdapterEnderecos listAdapterEnderecos = new ListaAdapterEnderecos(getContext(),listaCliente);
                    listViewLojasProximas.setAdapter(listAdapterEnderecos);

                    Utils utils = new Utils();
                    utils.setListViewHeightBasedOnChildren(listViewLojasProximas);

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

        btnMostraMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("nomes", listaNomes);
                bundle.putStringArrayList("enderecos", listaEnderecos);

                //Toast.makeText(getContext(),""+listaNomes,Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = ((PrincipalActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                MapaFragment mapaFragment = new MapaFragment();
                mapaFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.id_tela_principal, mapaFragment);
                fragmentTransaction.addToBackStack("Mapa").commit();

            }
        });

        return mView;
    }

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
