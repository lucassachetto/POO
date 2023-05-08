package main;
import java.util.ArrayList;

import java.sql.*;
import java.time.LocalDateTime;

public class Banco {

    private static Connection con;

    public static Boolean connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://200.148.39.206:3306/banco", "root", "Unesp2023*");
            
            System.out.println("Conectado com BD!");
            return true;
        } catch (Exception e) {

            System.out.println("Erro ao se conectar com o BD: " + e.getMessage());
            return false;
        }
    }

    public static void disconnectDb() {

        try {
            con.close();
            System.out.println("Desconectado!");
        } catch (Exception e) {
            System.out.println("Erro ao desconectar: "+e.getMessage());
        }
       
    }

    public static Long insere(String SQL, ArrayList<Object> params) {

        Long insertedId = null;
        
        try {
            
            PreparedStatement statement = getStatementWithParams(SQL, params, Statement.RETURN_GENERATED_KEYS);
            
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        insertedId = generatedKeys.getLong(1);
                    }
                    else {
                        System.out.println("Erro ao inserir registro: Creating user failed, no ID obtained.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }

        return insertedId;
    }

    public static ResultSet get(String SQL, ArrayList<Object> params) {

        ResultSet result = null;

        try {
            
            PreparedStatement statement = getStatementWithParams(SQL, params, Statement.NO_GENERATED_KEYS);
            
            result = statement.executeQuery();

        } catch (Exception e) {
            System.out.println("Erro ao tentar encontrar registro: "+e.getMessage());
        }

        return result;
    }
    
    public static Boolean atualiza(String SQL, ArrayList<Object> params) {

        Boolean isSuccess = false;
        
        try {
            
            PreparedStatement statement = getStatementWithParams(SQL, params, Statement.NO_GENERATED_KEYS);
            
            int affectedRows = statement.executeUpdate();

            isSuccess = affectedRows > 0;

        } catch (Exception e) {
            System.out.println("Erro ao criar usuario: "+e.getMessage());
        }

        return isSuccess;
    }

    private static PreparedStatement getStatementWithParams(String SQL, ArrayList<Object> params, int statementOptions) {

        PreparedStatement statement = null;
        try {
            
            if (con == null) {
                connectDb();
            }

            statement = con.prepareStatement(SQL, statementOptions);
            int paramIndex = 1;

            for (Object object : params) {
             
                switch (object.getClass().getSimpleName()) {
                    case "String":
                        statement.setString(paramIndex, (String)object);
                        break;
                    case "Integer":
                        statement.setInt(paramIndex, (Integer)object);
                        break;
                    case "Long":
                        statement.setLong(paramIndex, (Long)object);
                        break;
                    case "Double":
                        statement.setDouble(paramIndex, (Double)object);
                        break;
                    case "LocalDateTime":
                        statement.setTimestamp(paramIndex,Timestamp.valueOf(((LocalDateTime)object)));
                        break;                    
                    default:
                }

                paramIndex ++;
            }
        } catch(Exception e) {

        }

        return statement;
    }
 
}
