/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author joaorua
 */
public class Anos implements Serializable{
    private static final long serialVersionUID = 1L;
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
    
    public int numeroAutoresTotal(){
        TreeSet<String> todos = new TreeSet<String>();
        for (Ano a : anos.values()) {
            ArrayList<String> nomes = a.nomesAutores();
            for (int i=0; i < nomes.size(); i++){
                todos.add(nomes.get(i));
            }
        }
        return todos.size();
    }
    
    public int numeroAutoresSemCO(){
        int numero = 0;
        for (Ano a : anos.values()){
            numero+=a.semCoautores();
        }
        return numero;
    }
    
    public int numeroAutoresComCO(){
        ArrayList<String> total = new ArrayList<String>();
        for (Ano a : anos.values()){
            ArrayList<String> n_ano = a.nomesAutores();
            
            for (int i = 0; i < n_ano.size(); i++) {
                Autor autor = a.getAutor(n_ano.get(i));
                if (autor.temCoautores() && !total.contains(autor.getNome())) total.add(autor.getNome());
            }
        }
        return total.size();
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
        
    }
    
    public void addAutor(String a, String autor1){
        Ano ano;
        Autor autor;
        
        addAno(a);
        ano = getAno(a);
        ano.addAutor(autor1);
        
    }
    
    public int numeroAnos(){
        return anos.size();
    }
    
    
    public String anoInicial(){
        return anos.firstKey();
    }
    
    public String anoFinal(){
        return anos.lastKey();
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