import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Texto
class Texto {
    String desejo = "\nDigite o valor desejado do saque:";
    String menu = """
        \n
                Por favor insira uma das opções desejadas:
                1 - Sacar
                2 - Depositar
                3 - Consultar saldo
                4 - Emitir Extrato
                0 - Sair
                """;
    String insuficiente = "\nSaldo Insuficiente. Deposite a quantia desejada";
    String digite = "\nDigite a quantia desejada";
    String atualizado = "\nTudo certo. Já foi atualizado!!!";
    String erro = "\nHouve um erro";
}

// Classe Operacao (representa cada movimentação)
class Operacao {
    String tipo;
    float valor;

    Operacao(String tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo + ": " + (tipo.equals("Depósito") ? "+" : "-") + valor;
    }
}

// Classe ContaBancaria 
class ContaBancaria {
    private float saldo;
    private List<Operacao> extrato;
    private Texto textos;

    public ContaBancaria() {
        this.saldo = 200.0f; // saldo inicial
        this.extrato = new ArrayList<>();
        this.textos = new Texto();
    }

    public void sacar(float valor) {
        if (valor <= 0) {
            System.out.println(textos.erro);
            return;
        }

        if (valor > saldo) {
            System.out.println(textos.insuficiente);
        } else {
            saldo -= valor;
            extrato.add(new Operacao("Saque", valor));
            System.out.println(textos.atualizado);
        }
    }

    public void depositar(float valor) {
        if (valor <= 0) {
            System.out.println(textos.erro);
            return;
        }

        saldo += valor;
        extrato.add(new Operacao("Depósito", valor));
        System.out.println(textos.atualizado);
    }

    public void consultarSaldo() {
        System.out.println("Saldo atual: R$ " + saldo);
    }

    public void exibirExtrato() {
        System.out.println("\n=== Extrato ===");
        if (extrato.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (Operacao op : extrato) {
                System.out.println(op);
            }
        }
    }

    public Texto getTextos() {
        return textos;
    }
}

// Classe principal
public class POO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaBancaria conta = new ContaBancaria();
        Texto textos = conta.getTextos();

        int opcao;
        do {
            System.out.println(textos.menu);
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println(textos.digite);
                    float saque = scanner.nextFloat();
                    conta.sacar(saque);
                    break;
                case 2:
                    System.out.println(textos.digite);
                    float deposito = scanner.nextFloat();
                    conta.depositar(deposito);
                    break;
                case 3:
                    conta.consultarSaldo();
                    break;
                case 4:
                    conta.exibirExtrato();
                    break;
                case 0:
                    System.out.println("\nObrigada... Tchau!");
                    break;
                default:
                    System.out.println("\nOpção inválida");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
