/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author joaorua
 */
public class Ano {
    
    private String ano;
    private HashMap<String,Autor> autores;

    public Ano(){
        autores = new HashMap<String, Autor>();
    }
    
    public Ano(String ano){
        this.ano = ano;
        
        Autor a = new Autor();
        autores.put(ano, a);
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
