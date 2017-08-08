package com.sodadrink.br.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.HashMap;
import java.util.Map;

public class CadastrarActivity extends AppCompatActivity {

    Context context;

    Button btnCadastrarUsuario;

    EditText edtNome,edtLogin,edtSenha,edtEmail,edtCelular;


    RequestQueue requestQueue;

    String UrlInserirCliente = Enderecos.UrlInserirCliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        btnCadastrarUsuario = (Button)findViewById(R.id.btnCadastrarUsuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCelular = (EditText) findViewById(R.id.edtCelular);


        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(edtNome.getText().toString().isEmpty()){

                    edtNome.setError("Digite seu Nome");
                    edtNome.requestFocus();

                }else if(edtEmail.getText().toString().isEmpty()){

                    edtEmail.setError("Digite seu E-mail");
                    edtEmail.requestFocus();

                }else if(edtCelular.getText().toString().isEmpty()){

                    edtCelular.setError("Digite seu Numero de Celular");
                    edtCelular.requestFocus();

                } else if(edtLogin.getText().toString().isEmpty()){

                    edtLogin.setError("Digite seu Login");
                    edtLogin.requestFocus();

                }else if(edtSenha.getText().toString().isEmpty()){

                    edtSenha.setError("Digite sua Senha");
                    edtSenha.requestFocus();

                } else{

                    requestQueue = Volley.newRequestQueue(v.getContext());
                    StringRequest request = new StringRequest(Request.Method.POST, UrlInserirCliente, new Response.Listener<String>() {

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
                            Map<String, String> parametros = new HashMap<String, String>();


                            parametros.put("nome",edtNome.getText().toString());
                            parametros.put("login",edtLogin.getText().toString());
                            parametros.put("senha",edtSenha.getText().toString());
                            parametros.put("celular",edtCelular.getText().toString());
                            parametros.put("email",edtEmail.getText().toString());


                            return parametros;

                        }
                    };

                    requestQueue.add(request);

                    Toast.makeText(context,"Cadastrado com Sucesso!",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);

                }

            }
        });

    }

}
