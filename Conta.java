import java.util.ArrayList;

public class Conta {
    private int idUser;
    private int idConta;
    private double saldo;
    private ArrayList<Double> historico = new ArrayList<Double>();

    public Conta(int id) {
        this.idUser = id;
        this.idConta = Banco.getContaId();
    }

    public double getSaldo() {
        return saldo;
    }

    public void addOperacao(double valor) {
        historico.add(valor*-1);
        this.saldo += valor;
    }

    public void setSaldo(double valor) {
        this.saldo += valor;
    }

    public void verSaldo() {
        System.out.println("Saldo: "+ saldo);
    }

    public void verHistorico() {

        System.out.println("-------- Historico --------");
        for (Double value : historico) {
            String row = "";

            if (value < 0) {
                row = row + "Saque: ";
            } else {
                row = row + "Deposito: ";
            }

            System.out.println("\t"+row + value.toString());
        }
    }
}
