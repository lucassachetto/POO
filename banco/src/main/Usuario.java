package main;
public class Usuario {
    private Long id;
    private String nome;
    private String cpf;

    public Usuario(Long id, String nome, String cpf) {
    
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
