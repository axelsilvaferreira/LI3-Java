/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author joaorua
 */
public class Anos {
    private TreeMap<String,Ano> anos;

    public Anos(){
        anos = new TreeMap<String, Ano>();
    }
    

    public TreeMap<String, Ano> getAnos() {
        return anos;
    }
    
    
    
    public void setAnos(TreeMap<String, Ano> anos) {
        this.anos = anos;
    }
    
    public boolean existeAno(String ano){
        return anos.containsKey(ano);
    }
    
    public Ano getAno(String ano){
        return anos.get(ano);
    }
    public void addAno(String ano){
        if (!existeAno(ano)){
            Ano a = new Ano(ano);
            anos.put(ano, a);
        }
    }
    
    public void addAutorCoautor(String a, String autor1, String autor2){
        Ano ano;
        Autor autor;
        Coautores coautor;
        
        addAno(a);
        ano = getAno(a);
        ano.addAutor(autor1);
        autor = ano.getAutor(autor1);
        autor.addCoautor(autor2);
        
        /*
        if (existeAno(a)){
            ano = anos.get(a);
            if(ano.existeAutor(autor1)){
                autor = ano.getAutor(autor1);
                if(autor.existCoautor(autor2)) {
                    coautor = autor.getCoautor(autor2);
                } else {
                    coautor = new Coautores(autor2);
                }
            } else {
                autor = new Autor(autor1);
                coautor = new Coautores(autor2);
            }
        } else {
            ano = new Ano(a);
            autor = new Autor(autor1);
            coautor = new Coautores(autor2);   
        }
        */
        
    }
    //public void add
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        for (Ano a : anos.values()) {
            s.append(a.toString());
        }
        
        return s.toString();
    }
}
