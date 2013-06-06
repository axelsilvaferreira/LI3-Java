/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.ArrayList;
import java.lang.String;

/**
 *
 * @author joaorua
 */
public class LI3Java {

    /**
     * @param args the command line arguments
     */
    
    public static String[] trimAutorAno (String linha) {
        String[] dados = linha.split(",");
        
        /*
        for (int i = 0; i < campos.length; i++) {
            //if (i == campos.length-1) System.out.println("<<<ANO>>>");
            //System.out.println(campos[i].trim());
            
        }
        *
        */
        return dados;
        
    }
    
    public static void main(String[] args) {
        ArrayList<String> linhas;
        String[] dados;
        Anos anos = new Anos();
        Ano ano;
        Ano ano2;
        
        linhas = FileInput.txtInput();
        
        /*
        for (int i = 0; i < linhas.size(); i++) {
            dados = trimAutorAno(linhas.get(i));
        
        }
        */
        /*
        anos.addAno("2013");
        ano = anos.getAno("2013");
        ano.addAutor("Joao Rua");
        ano.addAutor("Joao Rua");
        anos.addAno("2000");
        ano2 = anos.getAno("2000");
        ano2.addAutor("Eduardo Pereira");
        ano2.addAutor("Joao Rua");
        
        */
        
        anos.addAutorCoautor("2013", "Joao Rua", "Eduardo Pereira");
        anos.addAutorCoautor("2013", "Joao Rua", "Miguel Barros");
        anos.addAutorCoautor("2013", "Joao Rua", "Miguel Barros");
        anos.addAutorCoautor("2013", "Eduardo Pereira", "Miguel Barros");
        anos.addAutorCoautor("2012", "Eduardo Pereira", "Miguel Barros");
        System.out.println(anos.toString());
    }

}
