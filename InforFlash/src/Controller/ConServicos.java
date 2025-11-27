package Controller;

import Model.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ConServicos {

    Conexao conexao = new Conexao();

    //Cadastro de Serviços
    public void Cadastrar(Servicos servico) {
        String sql = "INSERT INTO TBSERVICO(VALORSERV, TIPOSERV, STATUSERV) VALUES (?,?,?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setFloat(1, servico.getValor());
            psmt.setString(2, servico.getTipoServ());
            psmt.setInt(3, 1);
            psmt.executeUpdate();

            conexao.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    NumberFormat formatadorReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    //Listagem
    public Vector listar() {
        Vector lista = new Vector();
        String sql = "SELECT * FROM TBSERVICO"
                + " WHERE STATUSERV = 1"
                + " ORDER BY TIPOSERV ASC";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Servicos servico = new Servicos();
                servico.setCodigo(rs.getInt("IDSERVICO"));
                servico.setValor(rs.getInt("VALORSERV"));
                servico.setTipoServ(rs.getString("TIPOSERV"));

                Vector novaLinha = new Vector();

                novaLinha.addElement(servico.getTipoServ());
                
                novaLinha.addElement(formatadorReal.format(servico.getValor()));

                novaLinha.addElement(servico.getCodigo());

                lista.addElement(novaLinha);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        return lista;
    }

    public void editar(Servicos servico) {
        String sql = "UPDATE TBSERVICO "
                + " SET VALORSERV = ?,TIPOSERV = ? WHERE IDSERVICO = ?";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            psmt.setFloat(1, servico.getValor());
            psmt.setString(2, servico.getTipoServ());
            psmt.setInt(3, servico.getCodigo());
            psmt.executeUpdate();
            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public Servicos pesquisarTipoServ(int codigo) {
        String sql = "SELECT * "
                + "FROM TBSERVICO "
                + "WHERE IDSERVICO = ?";
        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            ResultSet rs = psmt.executeQuery();

            Servicos servico = new Servicos();

            while (rs.next()) {
                servico.setCodigo(rs.getInt("IDSERVICO"));
                servico.setValor(rs.getFloat("VALORSERV"));
                servico.setTipoServ(rs.getString("TIPOSERV"));
            }

            return servico;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }
    }

    public void excluir(int codigo) {
        String sql = "UPDATE TBSERVICO"
                + " SET STATUSERV = 0"
                + " WHERE IDSERVICO = ?";

        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            psmt.executeUpdate();
            conexao.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String[] pegarTodosTipos() {
        String sql = "SELECT TIPOSERV"
                + "   FROM TBSERVICO";

        String[] tipos = {};

        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            int contLinhas = 0;
            while (rs.next()) {
                contLinhas++;
            }

            PreparedStatement psmt2 = conexao.conectar().prepareStatement(sql);
            ResultSet rs2 = psmt2.executeQuery();
            tipos = new String[contLinhas];
            int i = 0;

            while (rs2.next()) {
                tipos[i] = rs2.getString("TIPOSERV");
                i++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return tipos;
    }

}
