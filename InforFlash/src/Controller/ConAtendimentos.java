/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConAtendimentos {

    Conexao conexao = new Conexao();

    public int pegarUltimoPedido() {
        int codPed = 0;

        String sql = "    SELECT IDPEDIDO "
                + "    FROM TBPEDIDO"
                + "    ORDER BY IDPEDIDO DESC"
                + "    LIMIT 1";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                codPed = rs.getInt("IDPEDIDO");
            }
            conexao.desconectar();
            return codPed;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pegar: " + ex);
        }
        return 0;
    }

    public void cadastrar(Atendimentos atendimento) {
        String sql = "INSERT INTO TBATENDIMENTO(CODSERVICO, CODPEDIDO, OBSATEND, QTDPROD, PRECOATEND) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, atendimento.getServico());
            psmt.setInt(2, atendimento.getPedido());
            psmt.setString(3, atendimento.getObs());
            psmt.setInt(4, atendimento.getQtd());
            psmt.setFloat(5, atendimento.getPreco());
            psmt.executeUpdate();

            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }

    }

    // 3. Formata o número
   // String valorFormatado = formatadorReal.format(valor);

    public Vector listar() {
        Vector lista = new Vector();
        String sql = "SELECT TIPOSERV, QTDPROD, PRECOATEND, IDPEDIDO, DATAPED, STATUSPED, NOMECLIENTE "
                + " FROM TBATENDIMENTO, TBSERVICO, TBPEDIDO, TBCLIENTE "
                + " WHERE IDSERVICO = CODSERVICO AND IDCLIENTE = CODCLIENTE AND IDPEDIDO = CODPEDIDO AND STATUSPED <> 'Cancelado'"
                + " ORDER BY STATUSPED ASC";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Atendimentos atendimento = new Atendimentos();
                Pedidos pedido = new Pedidos();

                atendimento.setTipoServ(rs.getString("TIPOSERV"));
                atendimento.setQtd(rs.getInt("QTDPROD"));
                atendimento.setPreco(rs.getFloat("PRECOATEND"));

                pedido.setCodigo(rs.getInt("IDPEDIDO"));
                pedido.setData(rs.getString("DATAPED"));
                pedido.setStatus(rs.getString("STATUSPED"));
                pedido.setNomeCiente(rs.getString("NOMECLIENTE"));

                Vector novaLinha = new Vector();

                novaLinha.addElement(atendimento.getTipoServ());
                novaLinha.addElement(atendimento.getQtd());
                
                novaLinha.addElement(Formatacoes.formatarMoeda(atendimento.getPreco()));

                novaLinha.addElement(pedido.getCodigo());
                novaLinha.addElement(pedido.getNomeCiente());
                novaLinha.addElement(pedido.getData());
                novaLinha.addElement(pedido.getStatus());

                lista.addElement(novaLinha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;

    }
    
    public Vector listarPorCliente(int codigo){
        Vector lista = new Vector();
        String sql = "SELECT TIPOSERV, QTDPROD, PRECOATEND, IDPEDIDO, DATAPED, STATUSPED, NOMECLIENTE "
                + " FROM TBATENDIMENTO, TBSERVICO, TBPEDIDO, TBCLIENTE "
                + " WHERE IDSERVICO = CODSERVICO AND IDCLIENTE = CODCLIENTE AND IDPEDIDO = CODPEDIDO AND STATUSPED <> 'Cancelado' AND CODCLIENTE = ?"
                + " ORDER BY STATUSPED ASC";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Atendimentos atendimento = new Atendimentos();
                Pedidos pedido = new Pedidos();

                atendimento.setTipoServ(rs.getString("TIPOSERV"));
                atendimento.setQtd(rs.getInt("QTDPROD"));
                atendimento.setPreco(rs.getFloat("PRECOATEND"));

                pedido.setCodigo(rs.getInt("IDPEDIDO"));
                pedido.setData(rs.getString("DATAPED"));
                pedido.setStatus(rs.getString("STATUSPED"));
                pedido.setNomeCiente(rs.getString("NOMECLIENTE"));

                Vector novaLinha = new Vector();

                novaLinha.addElement(atendimento.getTipoServ());
                novaLinha.addElement(atendimento.getQtd());
                
                novaLinha.addElement(Formatacoes.formatarMoeda(atendimento.getPreco()));

                novaLinha.addElement(pedido.getCodigo());
                novaLinha.addElement(pedido.getNomeCiente());
                novaLinha.addElement(pedido.getData());
                novaLinha.addElement(pedido.getStatus());

                lista.addElement(novaLinha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    
    

    public Atendimentos pegarAtendimento(int codigo) {
        String sql = "SELECT * FROM TBATENDIMENTO "
                + " WHERE IDATEND = ?";

        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            ResultSet rs = psmt.executeQuery();

            Atendimentos atendimento = new Atendimentos();

            while (rs.next()) {
                atendimento.setCodigo(rs.getInt("IDATEND"));
                atendimento.setServico(rs.getInt("CODSERVICO"));
                atendimento.setPedido(rs.getInt("CODPEDIDO"));
                atendimento.setObs(rs.getString("OBSATEND"));
                atendimento.setQtd(rs.getInt("QTDPROD"));
                atendimento.setPreco(rs.getFloat("PRECOATEND"));
            }

            return atendimento;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }

    }

}
