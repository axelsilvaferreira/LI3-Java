/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.Collection;

/**
 *
 * @author joaorua
 */
public class Parser {
    
    public static void trimAutorAno (String linha) {
        String[] campos = linha.split(",");
        
        for (int i = 0; i < campos.length; i++) {
            if (i == campos.length-1) System.out.println("<<<ANO>>>");
            System.out.println(campos[i].trim());
        }
        
        System.out.println();
    }
    
    
}
