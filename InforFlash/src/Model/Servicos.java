package Model;

public class Servicos {

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the tipoServ
     */
    public String getTipoServ() {
        return tipoServ;
    }

    /**
     * @param tipoServ the tipoServ to set
     */
    public void setTipoServ(String tipoServ) {
        this.tipoServ = tipoServ;
    }
    
    private int codigo;
    private float valor;
    private String tipoServ;
   
    
}
