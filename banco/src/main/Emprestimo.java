package main;
import Util.BancoException;

public class Emprestimo {
    
    private Long idEmprestimo;
    private Long idConta;
    private double valorEmprestado;
    private String status;
    private double valorPago;

    public Emprestimo(Long idEmprestimo, Long idConta, double valorEmprestado, String status, double valorPago ) {
        this.idEmprestimo = idEmprestimo;
        this.idConta = idConta;
        this.valorEmprestado = valorEmprestado;
        this.status = status;
        this.valorPago = valorPago;
    }

    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public Long getIdConta() {
        return idConta;
    }

    public String getStatus() {
        return status;
    }

    public Double getValorRestante() {
        return valorEmprestado - valorPago;
    }

    public double getValorEmprestado() {
        return valorEmprestado;
    }

    public void pagar(double valor) throws BancoException {
        if (valor <= getValorRestante()) {

                String novoStatus = "ATIVO";
                if (getValorEmprestado() == getValorPago() + valor) {
                    novoStatus = "PAGO";
                }
            EmprestimoHelper.pagarEmprestimo(this.idEmprestimo, valor+getValorPago(), novoStatus);
            Operacao.fazOperacao(getIdConta(), valor*-1, "PG EMPRESTIMO");
        } else {
            throw new BancoException("Valor a ser pago maior que o restante!");
        }
    }

    public double getValorPago() {
        return valorPago;
    }
}
