import java.util.ArrayList;

public class Conta {
    private Long idUser;
    private Long idConta;
    private double saldo;

    public Conta(Long idUsuario, Long idConta, Double saldo) {
        this.idUser = idUsuario;
        this.idConta = idConta;
        this.saldo = saldo;
    }

    public Long getIdConta() {
        return idConta;
    }

    public Long getIdUser() {
        return idUser;
    }

    public double getSaldo() {
        return saldo;
    }

    public void fazOperacao(double valor, String op) {

        Double novoSaldo = this.saldo + valor;
    
        Boolean opResult = ContaHelper.atualizaSaldo(idConta, novoSaldo);

        if (opResult) {
            HistoricoHelper.novoHistorico(idConta, valor, op);
            
            this.saldo = novoSaldo;
            System.out.println("Saldo Atualizado!");
        } else {
            System.out.println("Erro ao atualizar saldo");
        }
    }

    public void verSaldo() {
        System.out.println("Saldo: "+ saldo);
    }

    public void verHistorico() {

        ArrayList<HistoricoOperacoes> historicos = HistoricoHelper.getHistoricoByConta(idConta);

        System.out.println("-------- Historico --------\n");
        for (HistoricoOperacoes historico : historicos) {
            System.out.println(historico.toString());
        }
    }

    public void transferir(Long idContaDestino, Double valor) {
        if (isTransferable(valor)) {
            if (!ContaHelper.transfere(this, idContaDestino, valor)) {
                System.out.println("Falha na Trânsferencia!");
            };
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public Boolean isTransferable(Double valor) {
        return saldo >= valor;
    }
}
