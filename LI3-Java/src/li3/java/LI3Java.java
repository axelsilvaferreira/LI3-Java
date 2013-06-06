/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        TreeMap<String,Integer> statsAno = new TreeMap<String, Integer>();
        
        int n_nomes= 0;
        int n_artigos_umautor = 0;
        
        linhas = FileInput.txtInput();
        
        
        for (int i = 0; i < linhas.size(); i++) {
            dados = trimAutorAno(linhas.get(i));
            //System.out.println("|" + dados[dados.length-1].trim() + "|");
            if (dados.length > 2){
                for (int j = 0; j < dados.length-2; j++) {
                    for (int k = 0; k < dados.length-2; k++) {
                        if (j!=k) anos.addAutorCoautor(dados[dados.length-1].trim(),dados[j].trim(), dados[k].trim());
                    }
                }
            } else {
                anos.addAutor(dados[dados.length-1].trim(), dados[0].trim());
            }
            
            if (statsAno.containsKey(dados[dados.length-1].trim())){
                int valor = statsAno.get(dados[dados.length-1].trim());
                valor+=1;
                statsAno.put(dados[dados.length-1].trim(), valor);
            } else {
                statsAno.put(dados[dados.length-1].trim(), 1);
            }
            if (dados.length == 2) n_artigos_umautor+=1;
            n_nomes+=(dados.length-1);
        }
        
        System.out.println("Informações do Ficheiro:\n");
        System.out.println("Nome do Ficheiro: " + FileInput.nomeFicheiro());
        System.out.println("Numero de Artigos: " + linhas.size());
        System.out.println("Numero de Nomes Lidos: " + n_nomes);
        System.out.println("Nuemro de Autores Diferentes: " + anos.numeroAutoresTotal());
        System.out.println("Intervalo de anos: [" + anos.anoInicial() + " a " + anos.anoFinal() + "]");
        
        System.out.println("\nNumeros Gerais:\n");
        System.out.println("Numero Total de Autores: " + anos.numeroAutoresTotal());
        System.out.println("Numero de Arquivos com um unico Autor: " + n_artigos_umautor);
        System.out.println("Numero de Autores sem Coautores: " + anos.numeroAutoresSemCO());
        System.out.println("Numero de Autores com Coautores: " + anos.numeroAutoresComCO());
        
        System.out.println("\nNumero de Artigos por Ano:\n");
        for(Map.Entry<String,Integer> entry : statsAno.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + " => " + value);
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
