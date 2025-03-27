/*
 * Nome do Arquivo: <Menu.java>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Descrição: Classe principal que contém o método main e a lógica do menu,
 * para interação com o usuário e manipulação das contas bancárias.>
 */

package Parcial1.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Menu
public class Menu {
    
    private static final String ARQUIVO_CONTAS = "contas.txt";

    public static int menu(Scanner scanner) {
        int opcao;

        System.out.println("\n****** Menu Contas Bancárias ******");
        System.out.println("1. Criar/Abrir arquivo de contas");
        System.out.println("2. Inserir conta");
        System.out.println("3. Excluir conta");
        System.out.println("4. pesquisar conta");
        System.out.println("5. Imprimir lista de contas");
        System.out.println("6. Excluir arquivo de contas");     
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        return opcao;
    }

    private static void criarAbrirArquivoContas() {
        try {
            File arquivo = new File(ARQUIVO_CONTAS);
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo de contas criado: " + arquivo.getName());
            } else {
                System.out.println("Arquivo de contas já existe.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar/abrir o arquivo de contas.");
            e.printStackTrace();
        }
    }

    private static void inserirConta(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Digite o saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o tipo da conta (Corrente, Poupanca, Salario, Internacional): ");
        String tipoConta = scanner.nextLine();

        String dadosConta;
        switch (tipoConta.toLowerCase()) {
            case "corrente":
                System.out.print("Digite o limite do cheque especial: ");
                double limiteChequeEspecial = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha
                dadosConta = numeroConta + "," + saldo + "," + tipoConta + "," + limiteChequeEspecial;
                break;
            case "poupanca":
                System.out.print("Digite a taxa de juros: ");
                double taxaJuros = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha
                dadosConta = numeroConta + "," + saldo + "," + tipoConta + "," + taxaJuros;
                break;
            case "salario":
                System.out.print("Digite a empresa vinculada: ");
                String empresaVinculada = scanner.nextLine();
                dadosConta = numeroConta + "," + saldo + "," + tipoConta + "," + empresaVinculada;
                break;
            case "internacional":
                System.out.print("Digite a moeda estrangeira: ");
                String moedaEstrangeira = scanner.nextLine();
                dadosConta = numeroConta + "," + saldo + "," + tipoConta + "," + moedaEstrangeira;
                break;
            default:
                System.out.println("Tipo de conta inválido. Conta genérica criada.");
                dadosConta = numeroConta + "," + saldo + "," + "Generica";
        }

        try (FileWriter fw = new FileWriter(ARQUIVO_CONTAS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(dadosConta);
            System.out.println("Conta inserida com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao inserir dados no arquivo.");
            e.printStackTrace();
        }
    }

    private static void excluirConta(Scanner scanner) {
        System.out.print("Digite o número da conta a ser excluída: ");
        String numeroConta = scanner.nextLine();
        ArrayList<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.split(",")[0].equalsIgnoreCase(numeroConta)) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter(ARQUIVO_CONTAS);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String linha : linhas) {
                out.println(linha);
            }
            System.out.println("Conta excluída com sucesso (se encontrada)!");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    private static void pesquisarConta(Scanner scanner) {
        System.out.print("Digite o número da conta a ser pesquisada: ");
        String numeroConta = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTAS))) {
            String linha;
            boolean encontrado = false;
            while ((linha = br.readLine()) != null) {
                if (linha.split(",")[0].equalsIgnoreCase(numeroConta)) {
                    System.out.println("Conta encontrada: " + linha);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Conta não encontrada.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void imprimirListaContas() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTAS))) {
            String linha;
            System.out.println("\nLista de Contas Bancárias:");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void excluirArquivoContas() {
        File arquivo = new File(ARQUIVO_CONTAS);
        if (arquivo.delete()) {
            System.out.println("Arquivo de contas deletado: " + arquivo.getName());
        } else {
            System.out.println("Falha ao deletar o arquivo de contas.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            opcao = menu(scanner);

            switch (opcao) {
                case 1:
                    criarAbrirArquivoContas();
                    break;
                case 2:
                    inserirConta(scanner);
                    break;
                case 3:
                    excluirConta(scanner);
                    break;
                case 4:
                    pesquisarConta(scanner);
                    break;
                case 5:
                    imprimirListaContas();
                    break;
                case 6:
                    excluirArquivoContas();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("Pressione **Enter** para continuar...");
            scanner.nextLine();
            System.out.println("Continuando o programa...");
        } while (opcao != 7);

        scanner.close();
    }
}
    

