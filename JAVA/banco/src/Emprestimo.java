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
            EmprestimoHelper.pagarEmprestimo(this.idEmprestimo, valor+getValorPago());
        }
    }

    public double getValorPago() {
        return valorPago;
    }
}
