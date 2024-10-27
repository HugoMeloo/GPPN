package model;

public class Empresa {
    private String id;
    private String nome;
    private String endereco;
    private String telefone;
    private String tipoServ;
    private String email;
    private String image;


    public Empresa(String nome) {
        this.nome = nome;
    }

    public Empresa(String id, String nome, String endereco, String telefone, String tipoServ, String email, String image) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipoServ = tipoServ;
        this.email = email;
        this.image = image;

    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTipoServ() {
        return tipoServ;
    }

    public String getEmail() {
        return email;
    }
    public String getImage() {
        return image;
    }

}
