package model;

public class Produto {
    String nome = "";
    double preco = 0.0;
    int estoque = 0;

    public Produto(String nome, double preco, int qtdInicial) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = qtdInicial;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

}