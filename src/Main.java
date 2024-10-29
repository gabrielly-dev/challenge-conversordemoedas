import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static void exibirMenu(Scanner scanner) {
        System.out.println("*******************************");
        System.out.println("1 - Dólar >> Peso argentino");
        System.out.println("2 - Peso argentino >> Dólar");
        System.out.println("3 - Dólar >> Real brasileiro");
        System.out.println("4 - Real brasileiro >> Dólar");
        System.out.println("5 - Dólar >> Peso colombiano");
        System.out.println("6 - Peso colombiano >> Dólar");
        System.out.println("7 - Sair");
        System.out.print("Escolha uma opção válida: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenu(scanner);
            int opcao = scanner.nextInt();

            String moedaOrigem = "";
            String moedaDestino = "";
            double valorParaConverter;

            switch (opcao) {
                case 1:
                    moedaOrigem = "USD";
                    moedaDestino = "ARS";
                    break;
                case 2:
                    moedaOrigem = "ARS";
                    moedaDestino = "USD";
                    break;
                case 3:
                    moedaOrigem = "USD";
                    moedaDestino = "BRL";
                    break;
                case 4:
                    moedaOrigem = "BRL";
                    moedaDestino = "USD";
                    break;
                case 5:
                    moedaOrigem = "USD";
                    moedaDestino = "COP";
                    break;
                case 6:
                    moedaOrigem = "COP";
                    moedaDestino = "USD";
                    break;
                case 7:
                    System.out.println("Saindo...");
                    executando = false;
                    continue;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }

            if (opcao >= 1 && opcao <= 6) {
                try {
                    System.out.print("Digite o valor a ser convertido: ");
                    valorParaConverter = scanner.nextDouble();

                    HTTP http = new HTTP(moedaOrigem);
                    Exchangerate exchangerate = http.getExchangerate();
                    double taxaDeCambio = exchangerate.getTaxaDeCambio(moedaDestino);
                    double valorConvertido = valorParaConverter * taxaDeCambio;

                    System.out.printf("O valor de %.2f %s em %s é: %.2f %s%n",
                            valorParaConverter, moedaOrigem, moedaDestino, valorConvertido, moedaDestino);
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    scanner.next(); // Limpa o buffer
                } catch (Exception e) {
                    System.err.println("Erro ao processar a conversão: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }
}