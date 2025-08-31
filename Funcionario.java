package trabajo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa{
    private String funcao;
    private BigDecimal salario;

    public Funcionario(LocalDate nascimento, String nome, String funcao, BigDecimal salario) {
        super(nascimento, nome);
        this.funcao = funcao;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00", simbolos);

        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", nascimento=" + getNascimento().format(formatar) + '\''+
                ", funcao='" + funcao + '\'' +
                ", salario=" + formatoDecimal.format(getSalario()) +
                '}';
    }
}
