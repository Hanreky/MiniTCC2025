/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LABINFO
 */
public class Conexao {

    Connection conn = null;

    public Connection conectar() {
        String url = "jdbc:mysql://localhost/BDINFORFLASH";
        String usuario = "root";
        String senha = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, usuario, senha);

            return conn;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        } catch (Exception erro) {
            System.out.println("Erro ao conectar " + erro);
            return null;
        }

    }

    public void desconectar() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao desconectar " + e);
        }
    }
}
