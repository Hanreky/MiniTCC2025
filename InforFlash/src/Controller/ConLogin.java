/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConLogin {

    Conexao conn = new Conexao();

    public void cadastrar(Login login) {
        String sql = "INSERT INTO LOGIN(USUARIO, SENHA, STATUSLOGIN) "
                + "VALUES (?, ?, 1)";

        try {
            PreparedStatement psmt = conn.conectar().prepareStatement(sql);
            psmt.setString(1, login.getUsuario());
            psmt.setString(2, login.getSenha());
            psmt.executeUpdate();

            conn.desconectar();

        } catch (SQLException e) {
            if (e.getErrorCode() != 1062) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }

        }
    }

    public Login entrar(Login login) {
        String sql = "SELECT USUARIO, SENHA, STATUSFUNC"
                + " FROM LOGIN, TBFUNCIONARIO"
                + " WHERE SENHA = ? AND USUARIO = ? AND USUARIO = USUARIOFUNC AND STATUSFUNC = 1 AND STATUSLOGIN = 1";
        try {

            PreparedStatement psmt = conn.conectar().prepareStatement(sql);

            psmt.setString(1, login.getSenha());
            psmt.setString(2, login.getUsuario());

            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {

                login.setSenha(rs.getString("SENHA"));
                login.setUsuario(rs.getString("USUARIO"));

                login.setLoginValidado(true);

                return login;
            } else {
                return null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public Login listar() {
        Login login = new Login();
        String sql = "SELECT SENHA "
                + " FROM LOGIN"
                + " WHERE USUARIO = ? AND STATUSLOGIN = 1";
        try {
            PreparedStatement psmt = conn.conectar().prepareStatement(sql);

            psmt.setString(1, login.getUsuario());

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                login.setSenha(rs.getString("SENHA"));
            }

            return login;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public void editar(Login login) {
        String sql = "UPDATE LOGIN "
                + " SET SENHA = ? "
                + " WHERE USUARIO = ? AND STATUSLOGIN = 1";

        try {
            PreparedStatement psmt = conn.conectar().prepareStatement(sql);
            
            psmt.setString(1, login.getSenha());
            psmt.setString(2, login.getUsuario());
            
            psmt.executeUpdate();
            
            conn.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    public boolean verificarSeUsuarioExiste(String usuario) {
        String sql = "SELECT USUARIO FROM LOGIN";
        try {
            PreparedStatement psmt = conn.conectar().prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("USUARIO").equals(usuario)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
