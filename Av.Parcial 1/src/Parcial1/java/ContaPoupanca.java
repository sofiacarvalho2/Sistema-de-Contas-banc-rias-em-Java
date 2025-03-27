/*
 * Nome do Arquivo: <ContaPoupanca.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Subclasse de ContaBancaria que representa uma conta poupança,
 * com atributo adicional para taxa de juros.>
 */

package Parcial1.java;

// Conta Poupança
public class ContaPoupanca extends ContaBancaria{
    private double taxaJuros;

    public ContaPoupanca(String numeroConta, double saldo, double taxaJuros) {
        super(numeroConta, saldo);
        this.taxaJuros = taxaJuros;
    }

    public void aplicarJuros() {
        double juros = getSaldo() * taxaJuros;
        depositar(juros);
        System.out.println("Juros de R$" + juros + " aplicados.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Taxa de Juros: " + (taxaJuros * 100) + "%";
    }
}
