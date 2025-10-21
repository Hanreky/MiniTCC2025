/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConFuncionarios {
    Conexao conexao = new Conexao();
    
    public void cadastrar(Funcionarios funcionario) {
        String sql = "INSERT INTO TBFUNCIONARIO(NOMEFUNC, CELFUNC, CPFFUNC, EMAILFUNC) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, funcionario.getNome());
            psmt.setString(2, funcionario.getTelefone());
            psmt.setString(3, funcionario.getCpf());
            psmt.setString(4, funcionario.getEmail());
            psmt.executeUpdate();

            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
}
