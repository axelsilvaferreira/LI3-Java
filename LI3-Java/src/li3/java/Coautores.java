/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.Serializable;

/**
 *
 * @author joaorua
 */
public class Coautores implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private int artigos;

    public Coautores(String nome) {
        this.nome = nome;
        this.artigos = 1;
    }
    
    public Coautores(String nome, int artigos) {
        this.nome = nome;
        this.artigos = artigos;
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
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("\n Nome: ").append(nome);
        s.append("\n Nº Artigos: ").append(artigos);
        
        return s.toString();
    }
    
}
