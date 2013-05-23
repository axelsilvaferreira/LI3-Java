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
    public static void main(String[] args) {
        ArrayList<String> linhas;
        linhas = FileInput.txtInput();
        for (int i = 0; i < linhas.size(); i++) {
            Parser.trimAutorAno(linhas.get(i));
        }
    }

}
