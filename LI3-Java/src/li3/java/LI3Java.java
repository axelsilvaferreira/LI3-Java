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
       
        return dados;
        
    }
    
    public static void main(String[] args) {
        ArrayList<String> linhas;
        String[] dados;
        Anos anos = new Anos();
        Ano ano;
        Ano ano2;
        
        linhas = FileInput.txtInput();
        
        
        for (int i = 0; i < linhas.size(); i++) {
            dados = trimAutorAno(linhas.get(i));
            //System.out.println("|" + dados[dados.length-1].trim() + "|");
            for (int j = 0; j < dados.length-2; j++) {
                for (int k = 0; k < dados.length-2; k++) {
                    if (j!=k) anos.addAutorCoautor(dados[dados.length-1].trim(),dados[j].trim(), dados[k].trim());
                }
            }
        }
        
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
        /*
        anos.addAutorCoautor("2013", "Joao Rua", "Eduardo Pereira");
        anos.addAutorCoautor("2013", "Joao Rua", "Miguel Barros");
        anos.addAutorCoautor("2013", "Joao Rua", "Miguel Barros");
        anos.addAutorCoautor("2013", "Eduardo Pereira", "Miguel Barros");
        anos.addAutorCoautor("2012", "Eduardo Pereira", "Miguel Barros");
        */
        //System.out.println(anos.existeAno("2012"));
        //ano = anos.getAno("1984");
        //System.out.println(ano.toString());
    }

}
