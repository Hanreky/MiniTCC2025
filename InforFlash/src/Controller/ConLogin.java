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

    public Login entrar(Login login) { // mudar alguma coisa aqui.. so nao sei o que esta errado
        String sql = "SELECT * FROM LOGIN WHERE SENHA = ? AND USUARIO = ? ";
        try {
            
            PreparedStatement psmt = conn.conectar().prepareStatement(sql);
            
            psmt.setString(1, login.getSenha());
            psmt.setString(2, login.getUsuario());
            
            ResultSet rs = psmt.executeQuery();
            
            if(rs.next()){
                
                login.setSenha(rs.getString("SENHA"));
                login.setUsuario(rs.getString("USUARIO"));
                
                login.setLoginValidado(true);
                
                return login;
            }
            else{
                return null;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
