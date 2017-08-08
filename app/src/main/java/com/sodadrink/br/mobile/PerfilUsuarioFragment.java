package com.sodadrink.br.mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilUsuarioFragment extends Fragment {

    String UrlMostrarClienteId = Enderecos.UrlMostrarClienteId();
    String UrlEditarCliente = Enderecos.UrlEditarCliente();

    TextView txtNome,txtEmail,txtCelular,txtLogin,txtSenha;

    int id_cliente;

    public PerfilUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_usuario, container, false);

        Button btnEditarUsuario = (Button)view.findViewById(R.id.btnEditarUsuario);

        txtNome = (TextView)view.findViewById(R.id.txtNome);
        txtEmail = (TextView)view.findViewById(R.id.txtEmail);
        txtCelular = (TextView)view.findViewById(R.id.txtCelular);
        txtLogin = (TextView)view.findViewById(R.id.txtLogin);
        txtSenha = (TextView)view.findViewById(R.id.txtSenha);

        Bundle bundle = getArguments();
        id_cliente = bundle.getInt("id_cliente");

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

                    txtNome.setText(nome);
                    txtEmail.setText(email);
                    txtCelular.setText(celular);
                    txtLogin.setText(login);
                    txtSenha.setText(senha);


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

        btnEditarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id_cliente",id_cliente);

                FragmentManager fragment = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragment.beginTransaction();

                EditarUsuarioFragment editarUsuarioFragment = new EditarUsuarioFragment();
                editarUsuarioFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.id_tela_principal, editarUsuarioFragment);

                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
