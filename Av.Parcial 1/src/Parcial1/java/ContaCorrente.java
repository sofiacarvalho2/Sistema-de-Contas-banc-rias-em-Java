/*
 * Nome do Arquivo: <ContaCorrente.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Subclasse de ContaBancaria que representa uma conta corrente,
 * com atributo adicional para limite de cheque especial.>
 */

package Parcial1.java;

// Conta Corrente
public class ContaCorrente extends ContaBancaria{
    private double limiteChequeEspecial;
    
    public ContaCorrente(String numeroConta, double saldo, double limiteChequeEspecial) {
        super(numeroConta, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= (getSaldo() + limiteChequeEspecial)) {
            super.sacar(valor);
        } else {
            System.out.println("Saldo e limite de cheque especial insuficientes.");
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Limite Cheque Especial: R$" + limiteChequeEspecial;
    }
}
