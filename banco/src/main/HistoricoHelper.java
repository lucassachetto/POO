package main;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoricoHelper {

    private static final String INSERT_HISTORICO = "INSERT INTO historico_operacoes (idconta,valor,descricao,datahora) VALUES (?,?,?,?)";
    private static final String GET_HISTORICO_BY_CONTA_ID = "SELECT idhistorico_operacoes,idconta,valor,descricao,datahora FROM historico_operacoes WHERE idConta = ? ORDER BY datahora DESC";

    public static Boolean novoHistorico(Long idConta, Double valor, String descricao) {

        LocalDateTime dt = LocalDateTime.now();

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        params.add(valor);
        params.add(descricao);
        params.add(dt);
     
        Long idHistorico = Banco.insere(INSERT_HISTORICO, params);

        return idHistorico != null;
    }

    public static ArrayList<HistoricoOperacao> getHistoricoByConta(Long idConta) {

        ArrayList<HistoricoOperacao> historicos = new ArrayList<HistoricoOperacao>();

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
       
        ResultSet rs = Banco.get(GET_HISTORICO_BY_CONTA_ID, params);
       
        try {
            while (rs.next()) {
               
                historicos.add(new HistoricoOperacao(rs.getLong(1), rs.getLong(2), rs.getDouble(3), rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return historicos;
    }
}
