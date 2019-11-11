package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/manutencao";
    private static final String USER = "root";
    private static final String PASS = "1234";

    //Metodo para conectar ao banco
    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao conectar ", ex);
        }
    }

    // Metodo para fechar Conexão
    public static void closeConnection(Connection con) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão " + ex.getMessage());
            }
        }
    }

    //Metodo para fechar conexão e preparedStatement
    public static void closeConnection(Connection con, PreparedStatement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar stmt " + ex.getMessage());
            } finally {
                closeConnection(con);
            }
        }
    }

    //Metodo para fechar conexão e preparedStatement
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar rs " + ex.getMessage());
            } finally {
                closeConnection(con, stmt);
            }
        }
    }

}
