package com.sodadrink.br.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarUsuarioFragment extends Fragment {

    String UrlMostrarClienteId = Enderecos.UrlMostrarClienteId();
    String UrlEditarCliente = Enderecos.UrlEditarCliente();

    Button btnAtualizarUsuario;

    EditText edtNome,edtLogin,edtSenha,edtEmail,edtCelular;

    int id_cliente;

    public EditarUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_usuario, container, false);

        Bundle bundle = getArguments();
        id_cliente = bundle.getInt("id_cliente");

        btnAtualizarUsuario = (Button) view.findViewById(R.id.btnAtualizarUsuario);

        edtNome = (EditText) view.findViewById(R.id.edtNome);
        edtLogin = (EditText) view.findViewById(R.id.edtLogin);
        edtSenha = (EditText) view.findViewById(R.id.edtSenha);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtCelular = (EditText) view.findViewById(R.id.edtCelular);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlMostrarClienteId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("CLIENTES");

                    JSONObject dadosCliente = jsonArray.getJSONObject(0);

                    String nome = dadosCliente.getString("nome");
                    String login = dadosCliente.getString("login");
                    String senha = dadosCliente.getString("senha");
                    String celular = dadosCliente.getString("celular");
                    String email = dadosCliente.getString("email");

                    edtNome.setText(nome);
                    edtEmail.setText(email);
                    edtCelular.setText(celular);
                    edtLogin.setText(login);
                    edtSenha.setText(senha);


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
                Map<String,String> parametros = new HashMap<>();

                parametros.put("id_cliente",""+id_cliente);

                return parametros;
            }
        };
        requestQueue.add(stringRequest);


        btnAtualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlEditarCliente, new Response.Listener<String>() {
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
                        Map<String,String> parametros = new HashMap<>();

                        parametros.put("id_cliente",""+id_cliente);

                        parametros.put("nome",edtNome.getText().toString());
                        parametros.put("login",edtLogin.getText().toString());
                        parametros.put("senha",edtSenha.getText().toString());
                        parametros.put("celular",edtCelular.getText().toString());
                        parametros.put("email",edtEmail.getText().toString());

                        return parametros;
                    }
                };
                requestQueue.add(stringRequest);

                new AlertDialog.Builder(getContext())
                        .setTitle("Sucesso!")
                        .setMessage("Perfil atualizado com sucesso!")
                        .setCancelable(true).show();

                /*Bundle bundle = new Bundle();
                bundle.putInt("id_cliente",id_cliente);

                FragmentManager fragment = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragment.beginTransaction();

                PerfilUsuarioFragment perfilUsuarioFragment = new PerfilUsuarioFragment();
                perfilUsuarioFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.id_tela_principal, perfilUsuarioFragment);

                fragmentTransaction.commit();*/

                FragmentManager fragmentManager = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

                //Toast.makeText(getContext(),"Atualizado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}
