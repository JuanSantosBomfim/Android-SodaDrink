package com.sodadrink.br.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id_cliente;
    String nome;

    int id_nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

        //*******OCULTAR ITENS DO MENU LATERAL***********
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.id_promocoes_cadastradas).setVisible(false);
        nav_Menu.findItem(R.id.id_perfil_usuario).setVisible(false);
        nav_Menu.findItem(R.id.id_pesquisa).setVisible(false);
        nav_Menu.findItem(R.id.id_resultado).setVisible(false);

        Bundle dadosRecebidos = getIntent().getExtras();

        if(dadosRecebidos != null){

            if(dadosRecebidos.getInt("id_nivel") != 0){

                id_cliente = dadosRecebidos.getInt("id_cliente");
                nome = dadosRecebidos.getString("nome");
                id_nivel = dadosRecebidos.getInt("id_nivel");

                nav_Menu.findItem(R.id.id_promocoes_cadastradas).setVisible(true);
                nav_Menu.findItem(R.id.id_pesquisa).setVisible(true);
                nav_Menu.findItem(R.id.id_resultado).setVisible(true);

            }else{

                id_cliente = dadosRecebidos.getInt("id_cliente");
                nome = dadosRecebidos.getString("nome");
                nav_Menu.findItem(R.id.id_promocoes_cadastradas).setVisible(true);
                nav_Menu.findItem(R.id.id_perfil_usuario).setVisible(true);

            }

        }else{

            nome = "Usuario não está logado.";

        }

    }

    //*************AO PRESSIONAR O BOTÃO DE VOLTAR NO CELULAR, É VERIFICADO SE EXISTE UM "addToBackStack("TAG")"
    //*************SE EXISTIR ELE PEGA ESSA TAG E VOLTA PARA O FRAGMENTO ANTERIOR
   /* @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);

        TextView txtNomeUsuarioMenu = (TextView)findViewById(R.id.txtNomeUsuarioMenu);
        txtNomeUsuarioMenu.setText(nome);

        return true;
    }

    /*View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(),"Cadastrado com Sucesso!",Toast.LENGTH_SHORT).show();

        }
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.id_home) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new HomeFragment()).commit();

        } else if (id == R.id.id_produto) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new VerificarProdutosFragment()).commit();

        } else if (id == R.id.id_promocoes_cadastradas) {

            Bundle bundle = new Bundle();
            bundle.putInt("id_cliente",id_cliente);

            FragmentManager fragment = (this).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragment.beginTransaction();

            PromocoesCadastradasFragment promocoesCadastradasFragment = new PromocoesCadastradasFragment();
            promocoesCadastradasFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.id_tela_principal, promocoesCadastradasFragment);

            fragmentTransaction.commit();

        } else if (id == R.id.id_promocao) {

            Bundle bundle = new Bundle();
            bundle.putInt("id_cliente",id_cliente);

            FragmentManager fragment = (this).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragment.beginTransaction();

            PromocoesFragment promocoesFragment = new PromocoesFragment();
            promocoesFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.id_tela_principal, promocoesFragment);

            fragmentTransaction.commit();
            //fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new PromocoesFragment()).commit();

        } else if (id == R.id.id_loja_proxima) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new LojasFragment()).commit();

        }  else if (id == R.id.id_fale_conosco) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new FaleConoscoFragment()).commit();

        } else if (id == R.id.id_sobre_empresa) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new SobreEmpresaFragment()).commit();

        } else if (id == R.id.id_perfil_usuario) {

            Bundle bundle = new Bundle();
            bundle.putInt("id_cliente",id_cliente);

            FragmentManager fragment = (this).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragment.beginTransaction();

            PerfilUsuarioFragment perfilUsuarioFragment = new PerfilUsuarioFragment();
            perfilUsuarioFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.id_tela_principal, perfilUsuarioFragment);

            fragmentTransaction.commit();

        }else if (id == R.id.id_login) {

            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.id_resultado) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new ResultadoPesquisaFragment()).commit();

        } else if (id == R.id.id_pesquisa) {

            fragmentManager.beginTransaction().replace(R.id.id_tela_principal,new PesquisaDeCampoFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
