import java.util.ArrayList;

import Util.BancoException;

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
    
        return this.saldo;
    }

    public void atualizaSaldo() throws BancoException {
        this.saldo = ContaHelper.getSaldoConta(this.idConta);
    }

    public void verHistorico() {

        ArrayList<HistoricoOperacao> historicos = HistoricoHelper.getHistoricoByConta(idConta);

        System.out.println("-------- Historico --------\n");
        for (HistoricoOperacao historico : historicos) {
            System.out.println(historico.toString());
        }
        System.out.println("\n");
    }

    public void pix(String cpfDestino, Double valor) throws BancoException {

        Conta contaDestino = ContaHelper.getContaByUserCpf(cpfDestino, "cc");
        
        if (contaDestino != null) {
            if (isTransferable(valor)) {
                Operacao.transferencia(this.idConta, contaDestino.getIdConta(), valor, "PIX");
                atualizaSaldo();
            } else {
                throw new BancoException("Saldo insuficiente!");
            }
        } else {
            throw new BancoException("Conta destino não encontrada!");
        }
    }

    //Transferencia de conta corrente para conta investimento ou vice e versa
    public void transferenciaInterna(Long idContaDestino, Double valor) throws BancoException {

        Conta contaDestino = ContaHelper.getContaById(idContaDestino);
        
        if (contaDestino != null && contaDestino.getIdUser() == this.idUser) {
            if (isTransferable(valor)) {
                Operacao.transferencia(this.idConta, idContaDestino, valor, "TRANSFERENCIA INTERNA");
                atualizaSaldo();
            } else {
                throw new BancoException("Saldo insuficiente!");
            }
        } else {
            throw new BancoException("Conta destino não encontrada!");
        }
    }

    public Boolean isTransferable(Double valor) {
        return saldo >= valor;
    }
}
