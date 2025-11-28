/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class Formatacoes {

    public static String formatarTelefone(String telefone) {

        try {
            if (telefone.length() == 11) {
                MaskFormatter formatador = new MaskFormatter("(##) #####-####");
                formatador.setValueContainsLiteralCharacters(false);

                return formatador.valueToString(telefone);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return telefone;
    }

    public static String formatarCpf(String CPF) {

        try {
            if (CPF.length() == 11) {
                MaskFormatter formatador = new MaskFormatter("###.###.###-##");
                formatador.setValueContainsLiteralCharacters(false);

                return formatador.valueToString(CPF);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return CPF;
    }

    static NumberFormat formatadorReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static String formatarMoeda(float valor) {
        return formatadorReal.format(valor);
    }
    
    public static float formatarDecimal(String num) {
        return Float.parseFloat(num.replace(',', '.'));
    }
    
    public static String formatarDecimal(float num){
        return String.valueOf(num).replace('.', ',');
    } 

}
