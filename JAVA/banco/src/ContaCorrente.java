public class ContaCorrente extends Conta {
    
    public ContaCorrente(Conta c) {
        super(c.getIdUser(), c.getIdConta(), c.getSaldo());
    }

    public void deposito(double valor) {

        if (valor > 0) {
            super.fazOperacao(valor, "DEPOSITO");
        } else {
            System.out.println("Valor inválido!");
        }
    }

    public void saque(double valor) {

        if (valor > 0) {
        
            if (super.isTransferable(valor)) {

                super.fazOperacao(valor*-1, "SAQUE       ");

            } else {
                System.out.println("Saldo insuficiente!");
            }
        }
        else {
            System.out.println("Valor inválido!");
        }
    }
}
