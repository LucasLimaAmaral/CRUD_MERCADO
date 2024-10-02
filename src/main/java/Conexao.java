
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author victo
 */
public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/POO_A4";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
    private static Connection conexao = null;

    // Método para retornar a conexão com o banco de dados
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Banco conectado com sucesso!");
            } catch (SQLException ex) {
                throw new RuntimeException("Erro na Conexao: ", ex);
            }
        }
        return conexao;
    }

    // Método para fechar a conexão
    public static void fecharConexao(Connection con) {
         {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Conexão fechada com sucesso.");
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        
        fecharConexao(con);
        
        try {
               if(stmt != null){
                   stmt.close();
               } 
        } catch (SQLException ex){
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
        
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        
        fecharConexao(con, stmt);
        
        try {
               if(rs != null){
                   rs.close();
               } 
        } catch (SQLException ex){
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
        
    }
}

