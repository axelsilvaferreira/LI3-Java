/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

/**
 *
 * @author joaorua
 */
public class Artigo {
    
    private String nome;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Artigo(String nome) {
        this.nome = nome;
    }
    
}
