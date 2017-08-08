package com.sodadrink.br.mobile;

/**
 * Created by 15251379 on 19/05/2017.
 */

public class Enderecos {

    //*********MUDAR TAMBEM NA API************
    static String ip = "http://192.168.1.1/";
    //static String ip = "http://www.sdrink.com.br/";

    //static String ip = "http://192.168.42.227/SodaDrink/";
    static String caminhoCMS = "CMS/";

    public static String UrlLogin(){

        String UrlLogin = ip + "api/verificarLogin.php";
        return UrlLogin;

    }

    public static String UrlMostrarSobre(){

        String UrlMostrarSobre = ip + "api/mostrarSobre.php";
        return UrlMostrarSobre;

    }

    public static String UrlBuscarProdutos(){

        String UrlBuscarProdutos = ip + "api/buscarProdutos.php";
        return UrlBuscarProdutos;

    }

    public static String UrlMostrarTodosResultadosPesquisa(){

        String UrlMostrarTodosResultadosPesquisa = ip + "api/mostrarTodosResultadoPesquisa.php";
        return UrlMostrarTodosResultadosPesquisa;

    }

    public static String UrlMostrarEnderecosClientes(){

        String UrlMostrarEnderecosClientes = ip + "api/mostrarEnderecoClientes.php";
        return UrlMostrarEnderecosClientes;

    }

    public static String UrlMostrarBanner(){

        String UrlMostrarBanner = ip + "api/mostrarBanner.php";
        return UrlMostrarBanner;

    }

    public static String UrlCadastrarPesquisa(){

        String UrlCadastrarPesquisa = ip + "api/cadastrarPesquisa.php";
        return UrlCadastrarPesquisa;

    }

    public static String UrlCadastrarClienteNaPromocao(){

        String UrlCadastrarClienteNaPromocao = ip + "api/cadastrarClienteNaPromocao.php";
        return UrlCadastrarClienteNaPromocao;

    }

    public static String UrlInserirCliente(){

        String UrlInserirCliente = ip + "api/cadastrarClientes.php";
        return UrlInserirCliente;

    }

    public static String UrlMostrarEstados(){

        String UrlMostrarEstados = ip + "api/mostrarEstados.php";
        return UrlMostrarEstados;

    }

    public static String UrlCadastrarFaleConosco(){

        String UrlCadastrarFaleConosco = ip + "api/cadastrarFaleConosco.php";
        return UrlCadastrarFaleConosco;

    }

    public static String UrlMostrarProduto(){

        String UrlMostrarProduto = ip + "api/mostrarProduto.php";
        return UrlMostrarProduto;

    }

    public static String UrlMostrarCliente(){

        String UrlMostrarCliente = ip + "api/mostrarClientes.php";
        return UrlMostrarCliente;

    }

    public static String UrlMostrarClienteId(){

        String UrlMostrarClienteId = ip + "api/mostrarClientePorId.php";
        return UrlMostrarClienteId;

    }

    public static String UrlEditarCliente(){

        String UrlEditarCliente = ip + "api/editarCliente.php";
        return UrlEditarCliente;

    }

    public static String UrlMostrarPromocao(){

        String UrlMostrarPromocao = ip + "api/mostrarPromocoes.php";
        return UrlMostrarPromocao;

    }

    public static String UrlMostrarPromocoesCadastradas(){

        String UrlMostrarPromocoesCadastradas = ip + "api/mostrarPromocoesCdastradas.php";
        return UrlMostrarPromocoesCadastradas;

    }

    public static String UrlMostrarResultadoPesquisa(){

        String UrlMostrarResultadoPesquisa = ip + "api/mostrarResultadoPesquisa.php";
        return UrlMostrarResultadoPesquisa;

    }

    public static String UrlMostrarParticipandoPromocoes(){

        String UrlMostrarParticipandoPromocoes = ip + "api/verificaSeEstaParticipandoPromocao.php";
        return UrlMostrarParticipandoPromocoes;

    }

    public static String CaminhoImagemNoCMS(){

        String CaminhoImagemNoCMS = ip + caminhoCMS;
        return CaminhoImagemNoCMS;

    }


    public static String CaminhoImagemNoSodaDrink(){

        String CaminhoImagemNoSodaDrink = ip ;
        return CaminhoImagemNoSodaDrink;

    }

}
