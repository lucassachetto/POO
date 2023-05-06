import Util.BancoException;

public class ContaCorrente extends Conta {
    
    private Emprestimo emprestimoAtivo;

    public ContaCorrente(Conta c) throws BancoException{
        super(c.getIdUser(), c.getIdConta(), c.getSaldo());

        this.emprestimoAtivo = EmprestimoHelper.getEmprestimoAtivoByConta(c.getIdConta());
    }

    public void deposito(double valor) throws BancoException {

        if (valor > 0) {

            Operacao.fazOperacao(getIdConta(), valor, "DEPOSITO             ");
            super.atualizaSaldo();

        } else {
            throw new BancoException("Valor inválido!");
        }
    }

    public void saque(double valor) throws BancoException {

        if (valor > 0) {
        
            if (super.isTransferable(valor)) {
                
                Operacao.fazOperacao(getIdConta(),valor*-1, "SAQUE               ");
                super.atualizaSaldo();
            } else {
                throw new BancoException("Saldo insuficiente!");
            }
        }
        else {
            throw new BancoException("Valor inválido!");
        }
    }

    public Emprestimo getEmprestimoAtivo() {
       return emprestimoAtivo;
    }

    public void setEmprestimoAtivo(Emprestimo emprestimoAtivo) {
        this.emprestimoAtivo = emprestimoAtivo;
    }

    public void fazerEmprestimo(double valor) throws BancoException {
        if (emprestimoAtivo != null) {
            if (valor <= maxValorEmprestimo()) {
                EmprestimoHelper.novoEmprestimo(getIdConta(), valor);
                setEmprestimoAtivo(EmprestimoHelper.getEmprestimoAtivoByConta(getIdConta()));
            }
        } else {
            throw new BancoException("Não é possível fazer outro emprestimo, já existe um em andamento!");
        }
    }

    private Double maxValorEmprestimo() throws BancoException {
        Double value = this.getSaldo()*5;
        if (value == 0) {
            value = 50.00;
        }
        return value;
    }
}
