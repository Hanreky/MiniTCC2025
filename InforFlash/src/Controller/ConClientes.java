package Controller;

import Model.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ConClientes {

    Conexao conexao = new Conexao();

    public void cadastrar(Clientes cliente) {
        String sql = "INSERT INTO TBCLIENTE(NOMECLIENTE, CELCLIENTE, CPFCLIENTE, EMAILCLIENTE) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, cliente.getNome());
            psmt.setString(2, cliente.getTelefone());
            psmt.setString(3, cliente.getCpf());
            psmt.setString(4, cliente.getEmail());
            psmt.executeUpdate();

            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public Vector listar() {
        Vector lista = new Vector();
        String sql = "SELECT * FROM TBCLIENTE";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setCodigo(rs.getInt("IDCLIENTE"));
                cliente.setNome(rs.getString("NOMECLIENTE"));
                cliente.setTelefone(rs.getString("CELCLIENTE"));
                cliente.setCpf(rs.getString("CPFCLIENTE"));
                cliente.setEmail(rs.getString("EMAILCLIENTE"));

                Vector novaLinha = new Vector();

                novaLinha.addElement(cliente.getNome());
                novaLinha.addElement(cliente.getTelefone());
                novaLinha.addElement(cliente.getCpf());

                lista.addElement(novaLinha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }

    public Clientes pesquisarCPF(String cpf) {
        String sql = "SELECT * "
                + "FROM TBCLIENTE "
                + "WHERE CPFCLIENTE = ?";
        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, cpf);
            ResultSet rs = psmt.executeQuery();

            Clientes cliente = new Clientes();

            while (rs.next()) {
                cliente.setCodigo(rs.getInt("IDCLIENTE"));
                cliente.setNome(rs.getString("NOMECLIENTE"));
                cliente.setTelefone(rs.getString("CELCLIENTE"));
                cliente.setCpf(rs.getString("CPFCLIENTE"));
                cliente.setEmail(rs.getString("EMAILCLIENTE"));
            }

            return cliente;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }
    }

    public void editar(Clientes cliente) {
        String sql = "UPDATE TBCLIENTE "
                + " SET NOMECLIENTE = ?,CELCLIENTE = ?, CPFCLIENTE = ?, EMAILCLIENTE = ? "
                + " WHERE IDCLIENTE = ?";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            psmt.setString(1, cliente.getNome());
            psmt.setString(2, cliente.getTelefone());
            psmt.setString(3, cliente.getCpf());
            psmt.setString(4, cliente.getEmail());
            psmt.setInt(5, cliente.getCodigo());
            psmt.executeUpdate();
            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public Clientes pegarNome(int codigo) {
        String sql = "SELECT NOMECLIENTE FROM TBCLIENTE "
                + " WHERE IDCLIENTE = ?";

        try {
            Clientes cliente = new Clientes();

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                cliente.setNome(rs.getString("NOMECLIENTE"));
            }
            
            conexao.desconectar();
            
            return cliente;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public void excluir(int codigo) {
        String sql = "DELETE FROM TBCLIENTE"
                + " WHERE IDCLIENTE = ?";

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
