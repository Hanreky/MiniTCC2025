package InforFlash;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author User
 */
import View.*;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Iniciar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Erro: " + ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmInicio iniciar = new frmInicio();
                iniciar.setVisible(true);
            }
        });

    }
    //TODO: Tempo API, Corrigir qual funcionário está envolvido no pedido(feito), 
    // aplicar validar para todos(feito), mudar cursor, fazer dessa a main class(feito), mudar senha, não deixar mudar alguns campos, ver pedidos cancelados.
    //desconectar do bd nos métodos(feito), otimizar o código e eliminar redundâncias (prioridade baixa no momento), editar obs(nao), excluir conta(feito)
}
