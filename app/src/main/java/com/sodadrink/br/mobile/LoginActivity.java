package com.sodadrink.br.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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


public class LoginActivity extends AppCompatActivity {

    String UrlLogin = Enderecos.UrlLogin();

    Context context;
    Button btnLogin,btnCadastrar;
    EditText edtNome,edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

        edtNome = (EditText)findViewById(R.id.edtNomeUsuario);
        edtSenha = (EditText)findViewById(R.id.editSenhaUsuario);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               loginUsuario();

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,CadastrarActivity.class);
                startActivity(intent);

            }
        });

    }

    public void loginUsuario(){

        String login = edtNome.getText().toString();
        String senha = edtSenha.getText().toString();

        if(login.isEmpty()){

            edtNome.setError("Preencha o email");
            edtNome.requestFocus();

        }else if(senha.isEmpty()){

            edtSenha.setError("Preencha a senha");
            edtSenha.requestFocus();

        }else{

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlLogin, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject = new JSONObject(response);

                        if(jsonObject.names().get(0).equals("DADOS")){

                            JSONArray jsonArray = jsonObject.getJSONArray("DADOS");

                            JSONObject objectjFinal = jsonArray.getJSONObject(0);

                            int id_cliente = objectjFinal.getInt("id_cliente");
                            String nome = objectjFinal.getString("nome");

                            //Toast.makeText(context,"BEM VINDO - "+nome, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context,PrincipalActivity.class);
                            intent.putExtra("id_cliente", id_cliente);
                            intent.putExtra("nome", nome);
                            startActivity(intent);

                        }else if(jsonObject.names().get(0).equals("DADOSFUNCIONARIO")){

                            JSONArray jsonArray = jsonObject.getJSONArray("DADOSFUNCIONARIO");

                            JSONObject objectjFinal = jsonArray.getJSONObject(0);

                            int id_usuario = objectjFinal.getInt("id_usuario");
                            String nome = objectjFinal.getString("nome");
                            int id_nivel = objectjFinal.getInt("id_nivel");

                            //Toast.makeText(context,"BEM VINDO - "+nome, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context,PrincipalActivity.class);
                            intent.putExtra("id_cliente", id_usuario);
                            intent.putExtra("nome", nome);
                            intent.putExtra("id_nivel", id_nivel);
                            startActivity(intent);

                        }else if(jsonObject.names().get(0).equals("INCORRETO")){

                            Toast.makeText(context,"Email ou Senha Invalido!",Toast.LENGTH_SHORT).show();

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
                    Map<String,String> parametros = new HashMap<String,String>();

                    parametros.put("login",login);
                    parametros.put("senha",senha);

                    return parametros;
                }
            };

            requestQueue.add(stringRequest);

        }

    }

}
