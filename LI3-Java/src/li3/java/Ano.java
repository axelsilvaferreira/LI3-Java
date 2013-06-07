/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author joaorua
 */
public class Ano implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String ano;
    private HashMap<String,Autor> autores;

    public Ano(){
        autores = new HashMap<String, Autor>();
    }
    
    public Ano(String ano){
        this.ano = ano;
        
        //Autor a = new Autor();
        autores = new HashMap<String, Autor>();
        //autores.put(ano, a);
    }
    
    public Ano(HashMap<String, Autor> autores) {
        this.autores = autores;
    }

    public HashMap<String, Autor> getAutores() {
        return autores;
    }

    public void setAutores(HashMap<String, Autor> autores) {
        this.autores = autores;
    }
    
    public Autor getAutor(String autor){
        return autores.get(autor);
    }
    
    public boolean existeAutor(String autor){
        return autores.containsKey(autor);
    }
    
    public void addAutor (String autor){
        Autor a;
        int artigos;
        if (existeAutor(autor)) {
            a = autores.get(autor);
            artigos = a.getArtigos();
            a.setArtigos(artigos+1);
        } else {
            a = new Autor(autor);
            autores.put(autor, a);
        }
    }
    
    public int numeroAutores(){
        return autores.size();
    }
    
    public ArrayList<String> nomesAutores(){
        ArrayList<String> nomes = new ArrayList<String>();
        for (Autor a: autores.values()){
            nomes.add(a.getNome());
        }
        return nomes;
    }
    
    public int semCoautores(){
        int i =0;
        for (Autor a: autores.values()){
            //System.out.println(a.contaCoautores());
            if (a.contaCoautores() == 0){
                i+=1;
            }
        }
        return i;
    }
    
    public int comCoautores(){
        int i = 0;
        for (Autor a: autores.values()){
            if (a.contaCoautores() != 0){
                i+=1;
            }
        }
        return i;
    }
    
    public HashMap<String,Integer> topArtigos(){
        HashMap<String,Integer> top = new HashMap<String,Integer>();
        for (Autor a : autores.values()) {
            //System.out.println(a.getNome()+"|"+a.getArtigos());
            top.put(a.getNome(), a.getArtigos());
        }
        //System.out.println(top.toString());
        return top;
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("\nAno: ").append(ano).append("\n");
        for (Autor a : autores.values()) {
            s.append(a.toString());
        }
        s.append("\n");
        
        return s.toString();
    }
}
