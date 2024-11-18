package model;

public class TipoItem {
    private int id;
    private String descricao;

    // Construtor vazio
    public TipoItem() {
    }

    // Construtor com parâmetros
    public TipoItem(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
