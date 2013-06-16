/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

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
        
        
        autores = new HashMap<String, Autor>();
    }
    
    public Ano(HashMap<String, Autor> autores) {
        this.autores = autores;
    }

    public String getAno(){
        return this.ano;
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
    
    public boolean existeCoautor(String coautor){
        return this.existeCoautor(coautor);
    }
    
    public boolean existeListaCoautores(String[] nomes){
        Integer i;
        Boolean flag=false;
        for (i = 0; i < nomes.length; i++) {
            flag=existeCoautor(nomes[i]);
            if(flag.equals(false)) break;
        }
        
        return flag;
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
    
    public ArrayList<String> nomesAutoresComCoautores(){
        ArrayList<String> nomes = new ArrayList<String>();
        for (Autor a: autores.values()){
            if(a.contaCoautores()>0){
                nomes.add(a.getNome());
            }
        }
        return nomes;
    }
    
    public int semCoautores(){
        int i =0;
        for (Autor a: autores.values()){
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
            top.put(a.getNome(), a.getArtigos());
        }
        return top;
    }
    
    public HashMap<String,Integer> topCOArtigos(){
        HashMap<String,Integer> top = new HashMap<String,Integer>();
        for (Autor a : autores.values()) {
            if (a.contaCoautores()>0){
                String autor = a.getNome();
                HashMap<String,Integer> co = a.coAutoresHash();
                for(Map.Entry<String,Integer> entry : co.entrySet()) {
                    String cnome = entry.getKey();
                    Integer arti = entry.getValue();
                    if (!top.containsKey(cnome+","+autor)) top.put(autor+","+cnome, arti);
                }
            }
            
        }
        return top;
    }
    
    public HashSet<String> listaDeCoautores(){
        HashSet<String> lista = new HashSet<String>();
        for(Autor a : autores.values()){
            if (a.contaCoautores() != 0){
                lista.add(a.getNome());
            }
        }
        
        return lista;
    }
    
    public HashSet<String> listaDeCoautoresNaoSolo(){
        HashSet<String> lista = new HashSet<String>();
        for(Autor a : autores.values()){
            if (!a.isSolo()){
                lista.add(a.getNome());
            }
        }
        
        return lista;
    }
    
    public HashSet<String> listaSemCoautores(){
        HashSet<String> lista = new HashSet<String>();
        for(Autor a : autores.values()){
            if (a.contaCoautores() == 0){
                lista.add(a.getNome());
            }
        }
        return lista;
    }
    public ArrayList<String> listaCoCom(String[] nomes){
        ArrayList<String> lista = new ArrayList<String>();
        for(Autor a : autores.values()){
            if(a.existeListaCo(nomes)){
                lista.add(a.getNome());
            }
        }
        return lista;
    }
    
    public TreeSet<String> listaAutores(){
        TreeSet<String> lista = new TreeSet<String>();
        for (Autor s : autores.values()) {
            lista.add(s.getNome());
        }
        return lista;
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
