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

    public void emprestimo(Double valor) {
        if (valor <= maxValorEmprestimo()) {
            super.fazOperacao(valor, "EMPRESTIMO");
        } else {
            System.out.println("Valor pedido maior que o limite pre aprovado que e de: "+maxValorEmprestimo().toString());
        }
    }

    public Double maxValorEmprestimo() {
        Double value = this.getSaldo()*5;
        if (value == 0) {
            value = 50.00;
        }
        return value;
    }
}
