package Controller;

    import Model.*;
    import java.sql.*;
    import java.util.Vector;
    import javax.swing.JOptionPane;

public class ConPedidos {
    
    Conexao conexao = new Conexao();
    
    public void cadastrar(Pedidos pedido){
        String sql = "INSERT INTO TBPEDIDO(CODFUNCIONARIO, CODCLIENTE, HORAPED, DATAPED, STATUSPED)"
                    +"VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, pedido.getFuncionario());
            psmt.setInt(2, pedido.getCliente());
            psmt.setString(3, pedido.getHora());
            psmt.setString(4, pedido.getData());
            psmt.setString(5, pedido.getStatus());
            psmt.executeUpdate();

            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        
    }
    
    public Vector listar() {
        Vector lista = new Vector();
        String sql = "SELECT * FROM TBPEDIDO";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setCodigo(rs.getInt("IDPEDIDO"));
                pedido.setFuncionario(rs.getInt("CODFUNCIONARIO"));
                pedido.setCliente(rs.getInt("CODCLIENTE"));
                pedido.setHora(rs.getString("HORAPED"));
                pedido.setData(rs.getString("DATAPED"));
                pedido.setStatus(rs.getString("STATUSPED"));

                Vector novaLinha = new Vector();
                
                novaLinha.addElement(pedido.getCodigo());
                novaLinha.addElement(pedido.getCliente());
                novaLinha.addElement(pedido.getFuncionario());
                novaLinha.addElement(pedido.getStatus());

                lista.addElement(novaLinha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
        
    }
    
    public Pedidos pegarPedido(int codigo) {
        String sql = "SELECT * FROM TBCLIENTE "
                + " WHERE IDPEDIDO = ?";

        try {

            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            ResultSet rs = psmt.executeQuery();

            Pedidos pedido = new Pedidos();

            while (rs.next()) {
                pedido.setCodigo(rs.getInt("IDSERVICO"));
                pedido.setFuncionario(rs.getInt("CODFUNCIONARIO"));
                pedido.setCliente(rs.getInt("CODCLIENTE"));
                pedido.setHora(rs.getString("HORAPED"));
                pedido.setData(rs.getString("DATAPED"));
                pedido.setStatus(rs.getString("STATUSPED"));
            }

            return pedido;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }

    }
    
    
    
    
    public void excluir(int codigo) {
        String sql = "DELETE FROM TBPEDIDO"
                + " WHERE IDPEDIDO = ?";

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
