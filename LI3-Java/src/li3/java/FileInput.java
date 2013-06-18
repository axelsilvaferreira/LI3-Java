/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author joaorua
 */
public class FileInput {
    
    public static final String ficheiro ="publicx.txt";
    
    public static Vector<String> txtInput (){
        Vector<String> linhas = new Vector<String> ();
        Scanner fichScan = null;
        try {
            fichScan = new Scanner(new FileReader(ficheiro));
            fichScan.useDelimiter(System.getProperty("line.separator"));
            while (fichScan.hasNext()) linhas.add(fichScan.next());
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return linhas;
    }
    
    public static ArrayList<String> txtInputA (){
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
    
    public static Vector<String> txtInput (String nome_ficheiro){
        Vector<String> linhas = new Vector<String> ();
        Scanner fichScan = null;
        try {
            fichScan = new Scanner(new FileReader(nome_ficheiro));
            fichScan.useDelimiter(System.getProperty("line.separator"));
            while (fichScan.hasNext()) linhas.add(fichScan.next());
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return linhas;
    }
    
    public static ArrayList<String> txtInputBuffer (String nome_ficheiro){
        ArrayList<String> linhas = new ArrayList<String> ();
        FileReader fichScan = null;
        BufferedReader br = null;
        String linha;
        try {
            fichScan = new FileReader(new File(nome_ficheiro));
            br = new BufferedReader(fichScan);
            //fichScan.use(System.getProperty("line.separator"));
            
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return linhas;
    }
    
    public static String nomeFicheiro(){
        return ficheiro;
    }
    
}
