/*
 * Nome do Arquivo: <ContaInternacional.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Subclasse de ContaBancaria que representa uma conta internacional,
 * com atributo adicional para moeda estrangeira.>
 */

package Parcial1.java;

// Conta Internacional
public class ContaInternacional extends ContaBancaria{
    private String moedaEstrangeira;

    public ContaInternacional(String numeroConta, double saldo, String moedaEstrangeira) {
        super(numeroConta, saldo);
        this.moedaEstrangeira = moedaEstrangeira;
    }

    @Override
    public String toString() {
        return super.toString() + ", Moeda Estrangeira: " + moedaEstrangeira;
    }
}
