import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Cliente cliente = null;
        Conta contaCorrente = null;
        Conta contaPoupanca = null;
        String menu = """
                Escolha uma opção:
                1 - Cadastrar Cliente
                2 - Depositar
                3 - Sacar
                4 - Transferir
                5 - Imprimir Extrato
                6 - Sair
                """;
        while (true) {
            System.out.println(menu);
            int opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    teclado.nextLine();  // Consumir a quebra de linha deixada pelo nextInt
                    String nomeCliente = teclado.nextLine();
                    cliente = new Cliente();
                    cliente.setNome(nomeCliente);

                    contaCorrente = new ContaCorrente(cliente);
                    contaPoupanca = new ContaPoupanca(cliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    if (cliente == null) {
                        System.out.println("Cadastre um cliente primeiro.");
                        break;
                    }
                    System.out.print("Digite o valor do depósito: ");
                    double valorDeposito = teclado.nextDouble();
                    contaCorrente.depositar(valorDeposito);
                    System.out.println("Depósito realizado com sucesso!");
                    contaCorrente.imprimirExtrato();
                    break;

                case 3:
                    if (cliente == null) {
                        System.out.println("Cadastre um cliente primeiro.");
                        break;
                    }
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = teclado.nextDouble();
                    contaCorrente.sacar(valorSaque);
                    System.out.println("Saque realizado com sucesso!");
                    contaCorrente.imprimirExtrato();
                    break;

                case 4:
                    if (cliente == null) {
                        System.out.println("Cadastre um cliente primeiro.");
                        break;
                    }
                    System.out.print("Digite o valor da transferência: ");
                    double valorTransferencia = teclado.nextDouble();
                    System.out.println("Escolha a conta para transferência:");
                    System.out.println("1 - Conta Corrente para Poupança");
                    System.out.println("2 - Conta Poupança para Corrente");
                    int escolhaConta = teclado.nextInt();

                    if (escolhaConta == 1) {
                        contaCorrente.transferir(valorTransferencia, contaPoupanca);
                        System.out.println("Transferência realizada da Conta Corrente para a Poupança com sucesso!");
                    } else if (escolhaConta == 2) {
                        contaPoupanca.transferir(valorTransferencia, contaCorrente);
                        System.out.println("Transferência realizada da Poupança para a Conta Corrente com sucesso!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 5:
                    if (cliente == null) {
                        System.out.println("Cadastre um cliente primeiro.");
                        break;
                    }
                    contaCorrente.imprimirExtrato();
                    contaPoupanca.imprimirExtrato();
                    break;

                case 6:
                    System.out.println("Encerrando o programa...");
                    teclado.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
