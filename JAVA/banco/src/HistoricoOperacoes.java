import java.time.LocalDateTime;

public class HistoricoOperacoes {
    private Long idHistorico;
    private Long idConta;
    private Double valor;
    private String descricao;
    private LocalDateTime datahora;

    public HistoricoOperacoes (Long idHistorico, Long idConta, Double valor, String descricao, LocalDateTime datahora) {
        this.idHistorico = idHistorico;
        this.idConta = idConta;
        this.valor = valor;
        this.descricao = descricao;
        this.datahora = datahora;
    }

    public Long getIdConta() {
        return idConta;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    @Override
    public String toString() {
            
        String msg = "\t"+descricao+"\t";

        msg = msg + valor.toString() + "\t" + datahora.toString();
    
        return msg;
    }

}