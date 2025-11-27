/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConFuncionarios {

    Conexao conexao = new Conexao();

    public void cadastrar(Funcionarios funcionario) {
        String sql = "INSERT INTO TBFUNCIONARIO(NOMEFUNC, CELFUNC, CPFFUNC, EMAILFUNC, USUARIOFUNC, STATUSFUNC) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Login login = new Login();

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, funcionario.getNome());
            psmt.setString(2, funcionario.getTelefone());
            psmt.setString(3, funcionario.getCpf());
            psmt.setString(4, funcionario.getEmail());
            psmt.setString(5, login.getUsuario());
            psmt.setInt(6, 1);
            psmt.executeUpdate();

            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public Funcionarios listar(Funcionarios funcionario) {
        String sql = "SELECT * "
                + " FROM TBFUNCIONARIO"
                + " WHERE USUARIOFUNC = ?";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            Login login = new Login();

            psmt.setString(1, login.getUsuario());

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                funcionario.setCodigo(rs.getInt("IDFUNCIONARIO"));
                funcionario.setNome(rs.getString("NOMEFUNC"));
                funcionario.setTelefone(rs.getString("CELFUNC"));
                funcionario.setCpf(rs.getString("CPFFUNC"));
                funcionario.setEmail(rs.getString("EMAILFUNC"));
            }

            return funcionario;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    public void editar(Funcionarios funcionario) {
        String sql = "UPDATE TBFUNCIONARIO "
                + " SET NOMEFUNC = ?,CELFUNC = ?, CPFFUNC = ?, EMAILFUNC = ? "
                + " WHERE IDFUNCIONARIO = ?";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            psmt.setString(1, funcionario.getNome());
            psmt.setString(2, funcionario.getTelefone());
            psmt.setString(3, funcionario.getCpf());
            psmt.setString(4, funcionario.getEmail());
            psmt.setInt(5, funcionario.getCodigo());
            psmt.executeUpdate();
            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean verificarCpf(String cpf) {
        String sql = "SELECT CPFFUNC"
                + " FROM TBFUNCIONARIO"
                + " WHERE STATUSFUNC = 1";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("CPFFUNC").equals(cpf)) {
                    return true;
                }
            }
            conexao.desconectar();
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public void pegarFuncionario(String usuario) {
        String sql = "SELECT IDFUNCIONARIO"
                + " FROM TBFUNCIONARIO"
                + " WHERE USUARIOFUNC = ?";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, usuario);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Funcionarios func = new Funcionarios();
                func.setCodigo(Integer.parseInt(rs.getString("IDFUNCIONARIO")));
            }
            conexao.desconectar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Funcionarios pegarFuncionario(Funcionarios func){
        String sql = "SELECT NOMEFUNC"
                + " FROM TBFUNCIONARIO"
                + " WHERE IDFUNCIONARIO = ?";
        try {
            Pedidos pedido = new Pedidos();
            
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, pedido.getFuncionario());
            pedido.setFuncionario(1);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                func.setNome(rs.getString("NOMEFUNC"));
            }
            
            conexao.desconectar();
            return func;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public void excluir(int codigo) {
        String sql = "UPDATE TBFUNCIONARIO, LOGIN"
                + " SET STATUSFUNC = 0, STATUSLOGIN = 0 "
                + " WHERE IDFUNCIONARIO = ? AND USUARIOFUNC = USUARIO";

        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            psmt.executeUpdate();
            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
