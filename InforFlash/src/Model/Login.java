package Model;

/**
 *
 * @author User
 */
public class Login {
    
    private static String usuario;
    private String senha;
    private static boolean loginValidado = false;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        Login.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLoginValidado() {
        return loginValidado;
    }

    public void setLoginValidado(boolean loginValidado) {
        Login.loginValidado = loginValidado;
    }
}
