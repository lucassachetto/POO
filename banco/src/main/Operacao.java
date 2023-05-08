package main;
import Util.BancoException;

public class Operacao {
    public static void fazOperacao(Long idConta, double valor, String op) throws BancoException {
        try {
            HistoricoHelper.novoHistorico(idConta, valor, op);
        } catch (Exception e) {
            throw new BancoException("Erro ao efetuar operação!");
        }
    }

    public static void transferencia(Long idConta, Long idContaDestino, Double valor, String op) throws BancoException {
            
        try{
            
            //Entra conta Destino
            fazOperacao(idContaDestino, valor, op);

            // Sai conta atual
            fazOperacao(idConta,valor*-1,op);
            
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }
    }
}
