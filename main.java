package trabajo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

//  Deve conter uma classe Principal para executar as seguintes ações
public class main {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        funcionarios.add(new Funcionario(LocalDate.of(2000,10,18),"Maria","Operador",new BigDecimal("2009.49")));
        funcionarios.add(new Funcionario(LocalDate.of(1990,5,12),"Joao","Operador",new BigDecimal("2284.38")));
        funcionarios.add(new Funcionario(LocalDate.of(1961,5,2),"Caio","Coordenador",new BigDecimal("9836.14")));
        funcionarios.add(new Funcionario(LocalDate.of(1988,10,14),"Miguel","Diretor",new BigDecimal("19119.88")));
        funcionarios.add(new Funcionario(LocalDate.of(1995,1,5),"Alice","Recepcionista",new BigDecimal("2234.68")));
        funcionarios.add(new Funcionario(LocalDate.of(1999,11,19),"Heitor","Operador",new BigDecimal("1582.72")));
        funcionarios.add(new Funcionario(LocalDate.of(1993,3,31),"Arthur","Contador",new BigDecimal("4071.84")));
        funcionarios.add(new Funcionario(LocalDate.of(1994,7,8),"Laura","Gerente",new BigDecimal("3017.45")));
        funcionarios.add(new Funcionario(LocalDate.of(2003,5,24),"Heloisa","Eletricista",new BigDecimal("1606.85")));
        funcionarios.add(new Funcionario(LocalDate.of(1996,9,2),"Helena","Gerente",new BigDecimal("2799.93")));


        /*
        * Imprimir todos os funcionários com todas as suas informações, sendo que:
        * • informação de data deve ser exibido no formato dd/mm/aaaa;
        * • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        * */
        funcionarios.stream()
                .forEach(System.out::println);


        //Remove o Joao da lista
        funcionarios.remove(funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equals("Joao"))
                .findFirst()
                .orElse(null)
        );

        //  Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //  Imprimir os funcionários, agrupados por função
        funcionariosPorFuncao.forEach((funcao,lista) ->{
            System.out.println("\nFuncao: "+funcao);
            lista.forEach(System.out::println);
        });

        //  Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(f -> {
            BigDecimal novoSalario = f.getSalario()
                    .multiply(new BigDecimal("1.10"))
                    .setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        });
        funcionarios.forEach(System.out::println);


        // Funcionarios que Nasceram no mes 10 e 12
        List<Funcionario> funcionarios10e12 = funcionarios.stream()
                .filter(funcionario -> funcionario.getNascimento().getMonthValue() == 10 || funcionario.getNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

       System.out.println(funcionarios10e12);

       // Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getNascimento))
                .orElse(null)
        ;
        int idade = Period.between(funcionarioMaisVelho.getNascimento(), LocalDate.now()).getYears();
        System.out.println("nome: "+funcionarioMaisVelho.getNome()+"\nIdade: "+idade);


        // Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.stream()
                .sorted((n1,n2) -> n1.getNome().compareTo(n2.getNome()))
                .forEach(System.out::println);

        //  Imprimir o total dos salários dos funcionários.
        BigDecimal somaSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        System.out.println("Valor total dos salários: " + decimalFormat.format(somaSalarios));


        //  Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + salariosMinimos + " salários mínimos.");
        }

    }
}
