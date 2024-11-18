package model;

public class Produto {
    private String idProduto;
    private String nomeProduto;
    private double quantidade;
    private double precoUni;
    private String codigoItem;
    private String tipoItem;
    private String image;

    public Produto(String nome) {
        this.nomeProduto = nome;
    }

    public Produto(String idProduto, String nome, double quantidadeProduto, double precoUnitario, String codigoItem, String tipoItem, String image) {
        this.idProduto = idProduto;
        this.nomeProduto = nome;
        this.quantidade = quantidadeProduto;
        this.precoUni = precoUnitario;
        this.codigoItem = codigoItem;
        this.tipoItem = tipoItem;
        this.image = image;

    }
    public String getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
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

    public String getTipoItem() {
        return tipoItem;
    }

    public String getImagemProduto() {
        return image;
    }
}
