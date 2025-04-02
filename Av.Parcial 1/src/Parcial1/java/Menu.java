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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


// Menu
public class Menu {
    
    private static final String ARQUIVO_CONTAS = "contas.txt";
    private static ArrayList<String> listaContas = new ArrayList<>(); // Correção: adicionar "static"

    public static int menu(Scanner scanner) {
        int opcao = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("\n****** Menu Contas Bancárias ******");
            System.out.println("1. Criar/Abrir arquivo de contas");
            System.out.println("2. Inserir conta");
            System.out.println("3. Deposito");
            System.out.println("4. Saque");
            System.out.println("5. Transferência");
            System.out.println("6. Pesquisar conta");
            System.out.println("7. Imprimir lista de contas");
            System.out.println("8. Excluir conta");
            System.out.println("9. Excluir arquivo de contas");     
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Digite um número inteiro.");
                scanner.nextInt();
            }
        }
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
            carregarContasDoArquivo();
        } catch (IOException e) {
            System.out.println("Erro ao criar/abrir o arquivo de contas.");
            e.printStackTrace();
        }
    }

    private static void inserirConta(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Digite o saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Digite o tipo da conta (Corrente, Poupanca, Salario, Internacional): ");
        String tipoConta = scanner.nextLine();

        String dadosConta;
        switch (tipoConta.toLowerCase())  {
            case "corrente":
                System.out.print("Digite o limite do cheque especial: ");
                double limiteChequeEspecial = scanner.nextDouble();
                scanner.nextLine();
                dadosConta = numeroConta + "," + saldoInicial + "," + tipoConta + "," + limiteChequeEspecial;
                break;
            case "poupanca":
                System.out.print("Digite a taxa de juros: ");
                double taxaJuros = scanner.nextDouble();
                scanner.nextLine();
                dadosConta = numeroConta + "," + saldoInicial + "," + tipoConta + "," + taxaJuros;
                break;
            case "salario":
                System.out.print("Digite a empresa vinculada: ");
                String empresaVinculada = scanner.nextLine();
                dadosConta = numeroConta + "," + saldoInicial + "," + tipoConta + "," + empresaVinculada;
                break;
            case "internacional":
                System.out.print("Digite a moeda estrangeira (língua e país. ex: en-US): ");
                String moedaEstrangeira = scanner.nextLine();
                dadosConta = numeroConta + "," + saldoInicial + "," + tipoConta + "," + moedaEstrangeira;
                break;
            default:
                System.out.println("Tipo de conta inválido. Conta genérica criada.");
                dadosConta = numeroConta + "," + saldoInicial + "," + "Generica";
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
        
        listaContas.add(dadosConta);
        salvarContasNoArquivo();
    }

    private static void depositar(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = validarNumeroConta(scanner);
        if (numeroConta == null) return;

        double valorDeposito = validarValor(scanner, "depósito");
        if (valorDeposito <= 0) return;

        for (int i = 0; i < listaContas.size(); i++) {
            String[] dadosConta = listaContas.get(i).split(",");
            if (dadosConta[0].equals(numeroConta)) {
                double saldo = Double.parseDouble(dadosConta[1]);
                saldo += valorDeposito;
                dadosConta[1] = String.valueOf(saldo);
                listaContas.set(i, String.join(",", dadosConta));
                salvarContasNoArquivo();
                System.out.println("Depósito realizado com sucesso. Novo saldo: " + formatarValor(saldo, "pt-BR")); // Modificação
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private static void sacar(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = validarNumeroConta(scanner);
        if (numeroConta == null) return;
    
        double valorSaque = validarValor(scanner, "saque");
        if (valorSaque <= 0) return;
    
        for (int i = 0; i < listaContas.size(); i++) {
            String[] dadosConta = listaContas.get(i).split(",");
            if (dadosConta[0].equals(numeroConta)) {
                double saldo = Double.parseDouble(dadosConta[1]);
                if (saldo >= valorSaque) {
                    saldo -= valorSaque;
                    dadosConta[1] = String.valueOf(saldo);
                    listaContas.set(i, String.join(",", dadosConta));
                    salvarContasNoArquivo();
                    System.out.println("Saque realizado com sucesso. Novo saldo: " + formatarValor(saldo, "pt-BR")); // Modificação
                } else {
                    System.out.println("Saldo insuficiente.");
                }
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private static void transferir(Scanner scanner) {
        
        System.out.print("Digite o número da conta de origem: ");
        String contaOrigem = validarNumeroConta(scanner);
        if (contaOrigem == null) return;

        System.out.print("Digite o número da conta de destino: ");
        String contaDestino = validarNumeroConta(scanner);
        if (contaDestino == null) return;

        double valorTranferencia = validarValor(scanner, "transferência");
        if (valorTranferencia <= 0) return;

        if (contaOrigem.equals(contaDestino)) {
            System.out.println("Conta de origem e destino devem ser diferentes.");
            return;
        }

        int indiceOrigem = -1;
        int indiceDestino = -1;

        for (int i = 0; i < listaContas.size(); i++) {
            String[] dadosConta = listaContas.get(i).split(",");
            if (dadosConta[0].equals(contaOrigem)) {
                indiceOrigem = i;
            }
            if (dadosConta[0].equals(contaDestino)) {
                indiceDestino = i;
            }
        }

        if (indiceOrigem == -1 || indiceDestino == -1){
            System.out.println("Conta de origem ou destino não encontrada");
            return;
        }
        
        String[] dadosOrigem = listaContas.get(indiceOrigem).split(",");
        String[] dadosDestino = listaContas.get(indiceDestino).split(",");

        double saldoOrigem = Double.parseDouble(dadosOrigem[1]);
        double saldoDestino = Double.parseDouble(dadosDestino[1]);

        if (saldoOrigem < valorTranferencia) {
            System.out.println("Saldo insuficiente na conta de origem.");
            return;
        }

        saldoOrigem -= valorTranferencia;
        saldoDestino += valorTranferencia;

        dadosOrigem[1] = String.valueOf(saldoOrigem);
        dadosDestino[1] = String.valueOf(saldoDestino);
        
        listaContas.set(indiceOrigem, String.join(",", dadosOrigem));
        listaContas.set(indiceDestino, String.join(",", dadosDestino));

        salvarContasNoArquivo();

        System.out.println("Transferência realizada com sucesso. Novo saldo da conta de origem: " + formatarValor(saldoOrigem, "pt-BR")); // Modificação
        System.out.println("Novo saldo da conta de destino: " + formatarValor(saldoDestino, "pt-BR")); // Modificação
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

        salvarContasNoArquivo();
    }

    private static void pesquisarConta(Scanner scanner) {
        System.out.print("Digite o número da conta a ser pesquisada: ");
        String numeroConta = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTAS))) {
            String linha;
            boolean encontrado = false;
            while ((linha = br.readLine()) != null) {
                String[] dadosConta = linha.split(",");
                if (dadosConta[0].equalsIgnoreCase(numeroConta)) {
                    double saldo = Double.parseDouble(dadosConta[1]);
                    String moeda = "pt-BR"; // Moeda padrão para outros tipos de conta
                    if (dadosConta.length > 3 && dadosConta[2].equalsIgnoreCase("internacional")) {
                        moeda = dadosConta[3]; // Usar moeda da conta internacional
                    }
                    System.out.println("Conta encontrada: " + dadosConta[0] + ", Saldo: " + formatarValor(saldo, moeda));
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
                String[] dadosConta = linha.split(",");
                double saldo = Double.parseDouble(dadosConta[1]);
                String moeda = "pt-BR"; // Moeda padrão para outros tipos de conta
                if (dadosConta.length > 3 && dadosConta[2].equalsIgnoreCase("internacional")) {
                    moeda = dadosConta[3]; // Usar moeda da conta internacional
                }
                System.out.println("Conta: " + dadosConta[0] + ", Saldo: " + formatarValor(saldo, moeda));
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

    private static String formatarValor(double valor, String moeda) {
        Locale local = new Locale.Builder().setLanguage(moeda.substring(0, 2)).setRegion(moeda.substring(3, 5)).build();
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(local);
        return formatoMoeda.format(valor);
    }

    private static String validarNumeroConta(Scanner scanner) {
        String numeroConta = scanner.nextLine();

        if (!numeroConta.matches("\\d+")) {
            System.out.println("Número de conta inválido. Digite apenas números.");
            return null;
        }

        // Verificar se a conta existe
        boolean contaExiste = false;
        for (String conta : listaContas) {
            if (conta.split(",")[0].equals(numeroConta)) {
                contaExiste = true;
                break;
            }
        }

        if (!contaExiste) {
            System.out.println("Conta não encontrada.");
            return null;
        }
        return numeroConta;     
    }


    private static double validarValor(Scanner scanner, String tipoOperacao) {
        double valor = 0;
        boolean entradaValida = false;
    
        while (!entradaValida) {
            System.out.print("Digite o valor do " + tipoOperacao + ": ");
            try {
                valor = scanner.nextDouble();
                System.out.println("Valor digitado: " + valor); // Adicionar esta linha
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Digite um número.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
        scanner.nextLine(); // Consumir a quebra de linha
    
        return valor;
    }


    private static void carregarContasDoArquivo() {
        listaContas.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                listaContas.add(linha);
            }
        } catch (IOException e) {
            // Se o arquivo não existir, não faz nada
        }
    }

    private static void salvarContasNoArquivo() {
        try (FileWriter fw = new FileWriter(ARQUIVO_CONTAS);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String linha : listaContas) {
                out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
            e.printStackTrace();
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
                    depositar(scanner);
                    break;
                case 4:
                    sacar(scanner);
                    break;
                case 5:
                    transferir(scanner);
                    break;
                case 6:
                    pesquisarConta(scanner);
                    break;
                case 7:
                    imprimirListaContas();
                    break;
                case 8:
                    excluirConta(scanner);
                    break;
                case 9:
                    excluirArquivoContas();
                    break;
                case 10:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("Pressione **Enter** para continuar...");
            scanner.nextLine();
            System.out.println("Continuando o programa...");
        } while (opcao != 9);

        scanner.close();
    }
}
    

