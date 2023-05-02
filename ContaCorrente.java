public class ContaCorrente extends Conta {
    
    public ContaCorrente(int idUser) {
        super(idUser);
    }

    public void deposito(double valor) {

        super.addOperacao(valor);
        System.out.println("Deposito sucedido!");
    }

    public void saque(double valor) {
        if (super.getSaldo() >= valor) {

            super.addOperacao(valor*-1);
            System.out.println("Saque bem sucedido!");

        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
}
