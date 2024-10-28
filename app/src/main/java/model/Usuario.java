package model;

public class Usuario {
    private String id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;

    public Usuario(String id, String nomeUs, String sobrenome, String endereco, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nomeUs;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
