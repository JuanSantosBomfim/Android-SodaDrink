package com.sodadrink.br.mobile;

/**
 * Created by Amongsk on 10/05/2017.
 */

public class Produto {

    private int id_produto;
    private int id_categoria;
    private int id_fornecedor;
    private int id_marca;
    private String nome;
    private float valorCompra;
    private String imagem;
    private float peso;
    private int codBarra;
    private int porcDesconto;
    private int aprovado;
    private float valorVenda;
    private int quantidadeEstoque;
    private int quantidadeEngradado;
    private int qtdParaOSite;

    private String nomeCategoria;
    private String nomeMarca;

    public Produto(int id_produto, int id_categoria, int id_fornecedor, int id_marca, String nome,
                   float valorCompra, String imagem, float peso, int codBarra, int porcDesconto, int aprovado,
                   float valorVenda, int quantidadeEstoque, int quantidadeEngradado, int qtdParaOSite, String nomeCategoria, String nomeMarca) {

        this.id_produto = id_produto;
        this.id_categoria = id_categoria;
        this.id_fornecedor = id_fornecedor;
        this.id_marca = id_marca;
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.imagem = imagem;
        this.peso = peso;
        this.codBarra = codBarra;
        this.porcDesconto = porcDesconto;
        this.aprovado = aprovado;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeEngradado = quantidadeEngradado;
        this.qtdParaOSite = qtdParaOSite;

        this.nomeCategoria = nomeCategoria;
        this.nomeMarca = nomeMarca;

    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(int codBarra) {
        this.codBarra = codBarra;
    }

    public int getPorcDesconto() {
        return porcDesconto;
    }

    public void setPorcDesconto(int porcDesconto) {
        this.porcDesconto = porcDesconto;
    }

    public int getAprovado() {
        return aprovado;
    }

    public void setAprovado(int aprovado) {
        this.aprovado = aprovado;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeEngradado() {
        return quantidadeEngradado;
    }

    public void setQuantidadeEngradado(int quantidadeEngradado) {
        this.quantidadeEngradado = quantidadeEngradado;
    }

    public int getQtdParaOSite() {
        return qtdParaOSite;
    }

    public void setQtdParaOSite(int qtdParaOSite) {
        this.qtdParaOSite = qtdParaOSite;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
