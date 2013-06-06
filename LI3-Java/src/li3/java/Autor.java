/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package li3.java;

import java.util.HashMap;

/**
 *
 * @author joaorua
 */

public class Autor {
    
    private String nome;
    private int artigos;
    private HashMap<String,Coautores> coautores;
    

    public Autor(){
        coautores = new HashMap<String, Coautores>();
    }
    
    public Autor(String nome, int artigos) {
        this.nome = nome;
        this.artigos = artigos;
    }

    public Autor(String nome) {
        this.nome = nome;
        this.artigos = 1;
        coautores = new HashMap<String, Coautores>();
    }
    
    public Autor(String nome, String coautor) {
        Coautores a;
        this.nome = nome;
        this.artigos = 1;
        a = new Coautores(coautor);
        coautores.put(coautor, a);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getArtigos() {
        return artigos;
    }

    public void setArtigos(int artigos) {
        this.artigos = artigos;
    }
    
    public Coautores getCoautor(String coautor) {
        return coautores.get(coautor);
    }
    
    public void addCoautor(String coautor) {
        Coautores a;
        if (existCoautor(coautor)){
            a = coautores.get(coautor);
            a.setArtigos(a.getArtigos()+1);
        } else {
            a = new Coautores(coautor);
            coautores.put(coautor, a);
        }
    }
    
    public boolean existCoautor(String coautor) {
        return coautores.containsKey(coautor);
    }
    
    public String toString (){
        StringBuilder s = new StringBuilder();
        
        s.append("\nAutor: ").append(nome);
        s.append("\nNº Artigos: ").append(artigos);
        if (coautores.isEmpty()) s.append("\n");
        for (Coautores c : coautores.values()) {
            s.append(c).append("\n");
        }
        
        return s.toString();
    }
}
