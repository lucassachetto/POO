package main;
import java.sql.ResultSet;
import java.util.ArrayList;

import Util.BancoException;

public class UsuarioHelper {

    private static final String INSERT_USUARIO = "INSERT INTO usuario (nome, cpf, senha) VALUES (?,?,?)";
    private static final String GET_USUARIO = "SELECT idUsuario,nome,cpf FROM usuario WHERE nome = ? AND senha = ?";

    public static Usuario novoUsuario(String nome, String senha, String cpf) {
        Usuario u = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(nome);
        params.add(cpf);
        params.add(senha);

        Long userId = Banco.insere(INSERT_USUARIO, params);

        if (userId != null) {
            u = new Usuario(userId, nome, cpf);
        }

        return u;
    }

    public static Usuario getUsuario(String nome, String senha) throws BancoException {
        Usuario u = null;

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(nome);
        params.add(senha);
        
        ResultSet rs = Banco.get(GET_USUARIO, params);
       
        try {
            while (rs.next()) {
               
                u = new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (u == null) {
            throw new BancoException("Usuário ou senha incorretos!");
        }

        return u;
    }
}   
