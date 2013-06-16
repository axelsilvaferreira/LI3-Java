/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
        HashSet<String> listaSEM = new HashSet<String>();
        HashSet<String> listaCOM = new HashSet<String>();
        
        
        for (Ano a : anos.values()){
            listaCOM.addAll(a.listaDeCoautores());
            listaSEM.addAll(a.listaSemCoautores());
        }
        listaSEM.removeAll(listaCOM);
        return listaSEM.size();
    }
    
    public int numeroAutoresComCO(){
        HashSet<String> autores = listaAutores();
        HashSet<String> lista = (HashSet<String>) autores.clone();
        
        for (String s: autores){
            for (Ano a: anos.values()){
                if (a.existeAutor(s)){
                    if ((a.getAutor(s)).isSolo()){
                        lista.remove(s);
                    }
                }
            }
        }
        
        return lista.size();
        
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
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        for (Ano a : anos.values()) {
            s.append(a.toString());
        }
        
        return s.toString();
    }

    public TreeSet<String> listaAutoresComCoaurores(){
        TreeSet<String> list = new TreeSet<String>();
        
        for(Ano a : anos.values()) {
            list.addAll(a.nomesAutoresComCoautores());
        }
        return list;   
    }
    
    public TreeSet<String> listaCoautoresDeAutorPorIntervalo(Integer anoi, Integer anof, String nome){
        TreeSet<String> lista = new TreeSet<String>();
        Ano ano;
        Autor autor;
        for (Integer i = anoi; i <= anof; i++) {
            ano = anos.get(i.toString());
            if (ano.existeAutor(nome)){
                autor = ano.getAutor(nome);
                if(autor.temCoautores()){
                    lista.addAll(autor.listaCoautores());
                }
            }
        }
        return lista;
    }
    
    public HashSet<String> listaAutores(){
        HashSet<String> lista = new HashSet<String>();
        for (Ano a: anos.values()){
            lista.addAll(a.listaAutores());
        }
        return lista;
    }
    
    public void limpar(){
        anos.clear();
    }
    
    public TreeSet<String> listaAnos(){
        TreeSet<String> list = new TreeSet<String>();
        
        for(Ano a : anos.values()) {
            list.add(a.getAno());
        }
        return list;   
    }

 




}
