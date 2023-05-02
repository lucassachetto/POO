import java.util.ArrayList;
import java.sql.ResultSet;

public class ContaHelper {

    private static final String GET_CONTA_BY_USER_AND_ID = "SELECT idusuario, idconta, saldo FROM conta WHERE idUsuario = ? AND idconta = ?";
    private static final String INSERT_CONTA_CORRENTE = "INSERT INTO conta (idusuario, saldo, tipo) VALUES (?,0,?)";
    private static final String GET_CONTA_BY_USER = "SELECT idusuario, idconta, saldo FROM conta WHERE idUsuario = ? AND tipo = ?";
    private static final String GET_CONTA_BY_ID = "SELECT idconta, idusuario, saldo FROM conta WHERE idconta = ?";
    private static final String ATUALIZA_SALDO = "UPDATE conta SET saldo = ? WHERE idconta = ?";

    public static Conta novaConta(Long idUsuario, String tipo) {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idUsuario);
        params.add(tipo);
     
        Long idContaCorrente = Banco.insere(INSERT_CONTA_CORRENTE, params);

        if (idContaCorrente != null) {
            c = new Conta(idUsuario, idContaCorrente, 0.00);
        }

        return c;
    }

    public static Conta getContaByUser(Long idUsuario, String tipo) {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idUsuario);
        params.add(tipo);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_USER, params);
       
        try {
            while (rs.next()) {
               
                c = new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    public static Conta getContaById(Long idConta) {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_ID, params);
       
        try {
            while (rs.next()) {
               
                c = new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    public static Boolean atualizaSaldo(Long idConta, Double novoSaldo) {

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(novoSaldo);
        params.add(idConta);

        return Banco.atualiza(ATUALIZA_SALDO, params);
    }

    public static Boolean transfere(Conta conta, Long idContaDestino, Double valor) {
        
        Boolean isSuccess = false;

        // Checa se a conta é do mesmo dono
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(conta.getIdUser());
        params.add(idContaDestino);

        ResultSet rs = Banco.get(GET_CONTA_BY_USER_AND_ID, params);

        Conta contaDestino = null;

        try {
            while (rs.next()) {
               
                contaDestino = new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3));
            }

            if (contaDestino != null) {
            
                //Entra conta Destino
                contaDestino.fazOperacao(valor, "TRANSFERENCIA");
    
                // Sai conta atual
                conta.fazOperacao(valor*-1, "TRANSFERENCIA");

                isSuccess = true;
            } else {
                isSuccess = false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            isSuccess = false;
        }

        return isSuccess;
    }
}
