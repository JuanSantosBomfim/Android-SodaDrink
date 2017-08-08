package com.sodadrink.br.mobile;

/**
 * Created by Amon on 20/05/2017.
 */

public class Promocao {

    int id_promocao;
    String descricao;
    int aprovado;
    String imagem;
    String validade;
    String passo1;
    String passo2;
    String passo3;

    int id_cliente;

    public Promocao(int id_promocao, String descricao, int aprovado, String imagem, String validade, String passo1, String passo2, String passo3, int id_cliente) {

        this.id_promocao = id_promocao;
        this.descricao = descricao;
        this.aprovado = aprovado;
        this.imagem = imagem;
        this.validade = validade;
        this.passo1 = passo1;
        this.passo2 = passo2;
        this.passo3 = passo3;
        this.id_cliente = id_cliente;

    }

    public int getId_promocao() {
        return id_promocao;
    }

    public void setId_promocao(int id_promocao) {
        this.id_promocao = id_promocao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAprovado() {
        return aprovado;
    }

    public void setAprovado(int aprovado) {
        this.aprovado = aprovado;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getPasso1() {
        return passo1;
    }

    public void setPasso1(String passo1) {
        this.passo1 = passo1;
    }

    public String getPasso2() {
        return passo2;
    }

    public void setPasso2(String passo2) {
        this.passo2 = passo2;
    }

    public String getPasso3() {
        return passo3;
    }

    public void setPasso3(String passo3) {
        this.passo3 = passo3;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
