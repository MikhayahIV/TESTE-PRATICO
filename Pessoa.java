package trabajo;

import java.time.LocalDate;

public  abstract class Pessoa {
    private String nome;
    private LocalDate nascimento;

    public Pessoa(LocalDate nascimento, String nome) {
        this.nascimento = nascimento;
        this.nome = nome;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }


}
