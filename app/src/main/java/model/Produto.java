package model;

public class Produto {
    private String id;
    private String nomeProduto;
    private double quantidade;
    private double precoUni;
    private String codigoItem;
    private int tipoItem;
    private String image;

    public Produto(String nome) {
        this.nomeProduto = nome;
    }

    public Produto(String id, String nome, double quantidadeProduto, double precoUnitario, String codigoItem, int tipoItem, String image) {
        this.id = id;
        this.nomeProduto = nome;
        this.quantidade = quantidadeProduto;
        this.precoUni = precoUnitario;
        this.codigoItem = codigoItem;
        this.tipoItem = tipoItem;
        this.image = image;

    }
    public String getId() {
        return id;
    }

    public String getProduto() {
        return nomeProduto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getPrecoUni() {
        return precoUni;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public int getTipoItem() {
        return tipoItem;
    }

    public String getImage() {
        return image;
    }
}
