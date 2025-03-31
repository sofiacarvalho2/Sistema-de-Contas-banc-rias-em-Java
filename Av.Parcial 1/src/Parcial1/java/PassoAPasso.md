/*
 * Nome do Arquivo: <PassoAPasso.md>
 * Autor: <Sofia D. Carvalho>
 * Data de Criação: <2025-03-27>
 * Versão: <1.0>
 * Descrição: <Descrição do Arquivo>
 */

# Sistema de Contas bancárias em Java

## Descrição do Projeto

Este projeto consiste em um sistema de gerenciamento de contas bancárias, implementado em Java com orientação a objetos. O sistema permite criar, manipular e consultar diferentes tipos de contas bancárias, armazenando os dados em um arquivo texto.

## Classes

* **ContaBancaria (Classe Base)**
    * Atributos:
        * `numero_conta` (String)
        * `saldo` (double)
    * Métodos:
        * `depositar(double valor)`
        * `sacar(double valor)`
        * `ver_saldo()`

* **ContaCorrente (Subclasse de ContaBancaria)**
    * Atributo extra:
        * `limite_cheque_especial` (double)

 * **ContaPoupança (Subclasse de ContaBancaria)**   
     * Atributo extra:
        * `taxa_juros` (double)

* **ContaSalario (Subclasse de ContaBancaria)**
    * Atributo extra:
        * `empresa_vinculada` (String)

* **ContaInternacional (Subclasse de ContaBancaria)**
    * Atributo extra:
        * `moeda_estrangeira` (String)

## Menu Principal

O programa principal exibirá um menu com as seguintes opções:

1.  Criar arquivo.
2.  Inserir dados.
3.  Excluir dado.
4.  Pesquisar.
5.  Exibir lista.
6.  Excluir arquivo de contas
7.  Sair.

## Passo a Passo

1.  **Configuração do Ambiente:**
    * Instalar o JDK (Java Development Kit).
    * Configurar um IDE (Integrated Development Environment) como VSCode ou IntelliJ IDEA.
    * Criar um novo projeto Java.

2.  **Criação das Classes:**
    * Criar a classe `ContaBancaria` com os atributos e métodos básicos.
    * Criar as subclasses `ContaCorrente`, `ContaPoupanca`, `ContaSalario` e `ContaInternacional`, herdando de `ContaBancaria` e adicionando os atributos específicos.

3.  **Implementação dos Métodos:**
    * Implementar os métodos `depositar()`, `sacar()` e `ver_saldo()` na classe `ContaBancaria`.
    * Implementar os métodos específicos de cada subclasse, se necessário.

4.  **Criação do Menu:**
    * Criar um loop principal para exibir o menu e receber a entrada do usuário.
    * Implementar as opções do menu:
        * **1. Criar arquivo:** Criar um arquivo de texto para armazenar os dados das contas.
        * **2. Inserir dados:** Permitir que o usuário insira os dados de uma nova conta e salve no arquivo.
        * **3. Excluir dado:** Permitir que o usuário exclua uma conta existente do arquivo.
        * **4. Pesquisar:** Permitir que o usuário pesquise uma conta por número e exiba os dados.
        * **5. Exibir lista:** Exibir a lista de todas as contas no arquivo.
        * **6. Excluir arquivo de contas** Permitir que o usuário exclua o arquivo de texto criado.
        * **7. Sair:** Encerrar o programa.

5.  **Manipulação do Arquivo:**
    * Utilizar classes de I/O do Java para ler e escrever no arquivo de texto.
    * Implementar a lógica para salvar e carregar os dados das contas no arquivo.

6.  **Testes:**
    * Testar todas as funcionalidades do programa, incluindo a criação de contas, depósitos, saques, exclusões, pesquisas e listagens.
    * Verificar se os dados estão sendo salvos e carregados corretamente do arquivo.

## Próximos Passos

* implementar métodos de formatação.
* Implementar uma interface que define as operações básicas de uma conta bancária.  ok
* Implementar validações de entrada do usuário. ok
* Adicionar funcionalidades extras, como transferência entre contas. ok
* Utilizar um banco de dados para armazenar os dados das contas.
* Criar uma interface gráfica para o programa.
