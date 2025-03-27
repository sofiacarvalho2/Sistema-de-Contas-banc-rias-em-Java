/*
 * Nome do Arquivo: <ContaSalario.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Subclasse de ContaBancaria que representa uma conta salário,
 * com atributo adicional para empresa vinculada.>
 */

package Parcial1.java;

// Conta Salario
public class ContaSalario extends ContaBancaria{
    private String empresaVinculada;

    public ContaSalario(String numeroConta, double saldo, String empresaVinculada) {
        super(numeroConta, saldo);
        this.empresaVinculada = empresaVinculada;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa Vinculada: " + empresaVinculada;
    }
}
