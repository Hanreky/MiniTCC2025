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
}
