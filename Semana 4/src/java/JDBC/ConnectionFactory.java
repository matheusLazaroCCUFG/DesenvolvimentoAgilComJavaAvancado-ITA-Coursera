/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
public class ConnectionFactory {
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexao() {
        try {
            Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:8080/coursera",
                "postgres",
                "admin"
            );
            
            return conexao;
        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao conectar banco de dados",
                e
            );
        }
    }
}
