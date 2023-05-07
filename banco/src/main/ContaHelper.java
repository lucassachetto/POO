package main;

import java.util.ArrayList;
import Util.BancoException;

import java.sql.ResultSet;

public class ContaHelper {

    private static final String INSERT_CONTA = "INSERT INTO conta (idusuario, tipo) VALUES (?,?)";
    private static final String GET_CONTA_BY_USER_TIPO = "SELECT idusuario, idconta,(SELECT sum(valor) FROM banco.historico_operacoes WHERE idconta = c.idconta),tipo FROM conta c WHERE idUsuario = ? AND tipo = ?";
    private static final String GET_CONTA_BY_USER = "SELECT idusuario, idconta,(SELECT sum(valor) FROM banco.historico_operacoes WHERE idconta = c.idconta),tipo FROM conta c WHERE idUsuario = ?";
    private static final String GET_CONTA_BY_USER_CPF = "SELECT c.idusuario, c.idconta FROM conta c INNER JOIN usuario u ON c.idusuario = u.idusuario WHERE u.cpf = ? AND c.tipo = ?";
    private static final String GET_CONTA_BY_ID = "SELECT idusuario, idconta,(SELECT sum(valor) FROM banco.historico_operacoes WHERE idconta = c.idconta),tipo FROM conta c WHERE idconta = ?";
    private static final String GET_CONTA_SALDO = "SELECT sum(valor) FROM banco.historico_operacoes WHERE idconta = ?";

    public static Conta novaConta(Long idUsuario, String tipo) throws BancoException {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idUsuario);
        params.add(tipo);

        Long idConta = null;
        
        try {
            idConta = Banco.insere(INSERT_CONTA, params);
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }
        
        if (idConta != null) {
            c = new Conta(idUsuario, idConta, 0.00, tipo);
        }

        return c;
    }

    public static Conta getContaByUserCpf(String cpf, String tipo) throws BancoException {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(cpf);
        params.add(tipo);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_USER_CPF, params);
       
        try {
            while (rs.next()) {
               
                c = new Conta(rs.getLong(1), rs.getLong(2),0.00,null );
            }
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }

        return c;
    }

    public static Conta getContaByUserIdTipo(Long idUsuario, String tipo) throws BancoException {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idUsuario);
        params.add(tipo);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_USER_TIPO, params);
       
        try {
            while (rs.next()) {
               
                c = new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3), rs.getString(4));
            }
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }

        return c;
    }

    public static ArrayList<Conta> getContaByUserId(Long idUsuario) throws BancoException {
        ArrayList<Conta> contas = new ArrayList<Conta>();

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idUsuario);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_USER, params);
       
        try {
            while (rs.next()) {
                contas.add(new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3), rs.getString(4)));
            }
        } catch (Exception e) {
            throw new BancoException(e.getMessage());
        }

        return contas;
    }

    public static Conta getContaById(Long idConta) throws BancoException {
        Conta c = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        
        ResultSet rs = Banco.get(GET_CONTA_BY_ID, params);
       
        try {
            while (rs.next()) {
               
                c = new Conta(rs.getLong(1), rs.getLong(2), rs.getDouble(3), rs.getString(4));
            }
        } catch (Exception e) {
            
            throw new BancoException(e.getMessage());
        }

        return c;
    }

    public static Double getSaldoConta (Long idConta) throws BancoException {
        Double saldo = 0.00;
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idConta);
        
        ResultSet rs = Banco.get(GET_CONTA_SALDO, params);
       
        try {
            while (rs.next()) {
               
                saldo = rs.getDouble(1);
            }
        } catch (Exception e) {
            throw new BancoException("Não foi possível encontrar o saldo da conta!");
        }

        return saldo;
    }
}
