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
import android.widget.RadioButton;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FaleConoscoFragment extends Fragment {

    String UrlCadastrarFaleConosco = Enderecos.UrlCadastrarFaleConosco();

    EditText edtNomeFaleConosco,edtEmailFaleConosco,edtTelefoneFaleConosco,edtCelularFaleConosco,edtComentarioFaleConosco;
    RadioButton rdoSugestaoFaleConosco,rdoCriticaFaleConosco;
    Button btnEnviarFaleConosco;

    public FaleConoscoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fale_conosco, container, false);

        edtNomeFaleConosco = (EditText)view.findViewById(R.id.edtNomeFaleConosco);
        edtEmailFaleConosco = (EditText)view.findViewById(R.id.edtEmailFaleConosco);

        rdoSugestaoFaleConosco = (RadioButton) view.findViewById(R.id.rdoSugestaoFaleConosco);
        rdoCriticaFaleConosco = (RadioButton) view.findViewById(R.id.rdoCriticaFaleConosco);

        edtTelefoneFaleConosco = (EditText)view.findViewById(R.id.edtTelefoneFaleConosco);
        edtCelularFaleConosco = (EditText)view.findViewById(R.id.edtCelularFaleConosco);

        edtComentarioFaleConosco = (EditText)view.findViewById(R.id.edtComentarioFaleConosco);

        btnEnviarFaleConosco = (Button) view.findViewById(R.id.btnEnviarFaleConosco);

        btnEnviarFaleConosco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtNomeFaleConosco.getText().toString().isEmpty()){

                    edtNomeFaleConosco.setError("Digite seu Nome");
                    edtNomeFaleConosco.requestFocus();

                }else if(edtEmailFaleConosco.getText().toString().isEmpty()){

                    edtEmailFaleConosco.setError("Digite seu E-mail");
                    edtEmailFaleConosco.requestFocus();

                }else if(rdoSugestaoFaleConosco.isChecked() == false && rdoCriticaFaleConosco.isChecked() == false){

                    Toast.makeText(getContext(),"Selecione o Tipo de comentario!",Toast.LENGTH_SHORT).show();

                }else if(edtTelefoneFaleConosco.getText().toString().isEmpty()){

                    edtTelefoneFaleConosco.setError("Digite seu Telefone");
                    edtTelefoneFaleConosco.requestFocus();

                }else if(edtCelularFaleConosco.getText().toString().isEmpty()){

                    edtCelularFaleConosco.setError("Digite seu Celular");
                    edtCelularFaleConosco.requestFocus();

                }else if(edtComentarioFaleConosco.getText().toString().isEmpty()){

                    edtComentarioFaleConosco.setError("Digite seu Comentario");
                    edtComentarioFaleConosco.requestFocus();

                }else{

                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(getContext());

                    StringRequest request = new StringRequest(Request.Method.POST, UrlCadastrarFaleConosco, new Response.Listener<String>() {

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

                            String tipo = "v";
                            if(rdoSugestaoFaleConosco.isChecked() == true){

                                tipo = "s";

                            }else if(rdoCriticaFaleConosco.isChecked() == true){

                                tipo = "c";

                            }

                            parametros.put("nome",edtNomeFaleConosco.getText().toString());
                            parametros.put("email",edtEmailFaleConosco.getText().toString());
                            parametros.put("tipo",tipo);
                            parametros.put("telefone",edtTelefoneFaleConosco.getText().toString());
                            parametros.put("celular",edtCelularFaleConosco.getText().toString());
                            parametros.put("comentario",edtComentarioFaleConosco.getText().toString());

                            return parametros;

                        }
                    };

                    requestQueue.add(request);

                    new AlertDialog.Builder(getContext())
                            .setTitle("Sucesso!")
                            .setMessage("Comentário Enviado!")
                            .setCancelable(true).show();

                    FragmentManager fragmentManager = ((PrincipalActivity)getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

                }

            }
        });

        return view;
    }

}
