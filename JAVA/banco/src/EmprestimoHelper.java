
import java.util.ArrayList;
import Util.BancoException;

import java.sql.ResultSet;

public class EmprestimoHelper {

    private static final String INSERT_EMPRESTIMO = "INSERT INTO banco.emprestimo (idconta, valoremprestado, status, valorpago) VALUES (?,?,?,?)";
    private static final String GET_EMPRESTIMO_BY_CONTA = "SELECT idemprestimo, idconta, valoremprestado, status, valorpago FROM banco.emprestimo WHERE idconta = ? AND status = 'ATIVO'";
    private static final String ATUALIZA_VALOR_PAGO = "UPDATE banco.emprestimo SET valorpago = ?";

    public static void novoEmprestimo(Long idConta, Double valorEmprestado) throws BancoException {
      
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        params.add(valorEmprestado);
        params.add("ATIVO");
        params.add(0.00);
        
        try {
            idConta = Banco.insere(INSERT_EMPRESTIMO, params);
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }
    }

    public static Emprestimo getEmprestimoAtivoByConta(Long idConta) throws BancoException {
        Emprestimo emprestimo = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        
        ResultSet rs = Banco.get(GET_EMPRESTIMO_BY_CONTA, params);
       
        try {
            while (rs.next()) {
               
                emprestimo = new Emprestimo(rs.getLong(1), rs.getLong(2), rs.getDouble(3),rs.getString(4), rs.getDouble(5));
            }
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }

        return emprestimo;
    }

    public static void pagarEmprestimo(Long idEmprestimo, double valor) throws BancoException {

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idEmprestimo);
        params.add(valor);

        Banco.atualiza(ATUALIZA_VALOR_PAGO, params);
    }
}
