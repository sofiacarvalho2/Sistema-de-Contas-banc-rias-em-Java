/*
 * Nome do Arquivo: <ContaBancaria.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Classe base abstrata para representar contas bancárias,
 * definindo atributos e métodos comuns a todos os tipos de conta.>
 */

package Parcial1.java;

// Conta Bancaria
abstract class ContaBancaria {
    private String numeroConta;
    private double saldo;

    public ContaBancaria(String numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void verSaldo() {
        System.out.println("Saldo da conta " + numeroConta + ": R$" + saldo);
    }

    @Override 
    public String toString() {
        return "Conta: " + numeroConta + ", saldo: R$" + saldo;
    }
}