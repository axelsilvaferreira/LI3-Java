/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joaorua
 */
public class FileInput {
    
    public static final String ficheiro ="publicx.txt";
    
    public static ArrayList<String> txtInput (){
        ArrayList<String> linhas = new ArrayList<String> ();
        Scanner fichScan = null;
        try {
            fichScan = new Scanner(new FileReader(ficheiro));
            fichScan.useDelimiter(System.getProperty("line.separator"));
            while (fichScan.hasNext()) linhas.add(fichScan.next());
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return linhas;
    }
    
    public static ArrayList<String> txtInput (String nome_ficheiro){
        ArrayList<String> linhas = new ArrayList<String> ();
        Scanner fichScan = null;
        try {
            fichScan = new Scanner(new FileReader(nome_ficheiro));
            fichScan.useDelimiter(System.getProperty("line.separator"));
            while (fichScan.hasNext()) linhas.add(fichScan.next());
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return linhas;
    }
    
    public static String nomeFicheiro(){
        return ficheiro;
    }
    
}
