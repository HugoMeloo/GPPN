package model;

public class Usuario {
    private String nome;
    private String usuario; // email
    private String senha;
    private String endereco;
    private String telefone;
    private String confirSenha;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getConfirSenha() {
        return confirSenha;
    }

    public void setConfirSenha(String confirSenha) {
        this.confirSenha = confirSenha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
