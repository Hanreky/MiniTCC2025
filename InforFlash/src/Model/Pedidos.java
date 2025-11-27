 package Model;

public class Pedidos {
    
    private int codigo;
    private int cliente;
    private static int funcionario;
    private String hora, data, status, nomeCiente;

    
    public int getFuncionario() {
        return funcionario;
    }

    
    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    
    public int getCliente() {
        return cliente;
    }

    
    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    
    public int getCodigo() {
        return codigo;
    }

    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    public String getHora() {
        return hora;
    }

    
    public void setHora(String hora) {
        this.hora = hora;
    }

    
    public String getData() {
        return data;
    }

    
    public void setData(String data) {
        this.data = data;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public String getNomeCiente() {
        return nomeCiente;
    }

    public void setNomeCiente(String nomeCiente) {
        this.nomeCiente = nomeCiente;
    }
    
}
