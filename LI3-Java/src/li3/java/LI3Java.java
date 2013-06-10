/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Iterator;


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
   
    public static void menuConsultasInteractivas(){
        System.out.println("\nConsultas interactivas:\n");
        System.out.println("1 - Consultas indexadas por ano ou anos");
        System.out.println("2.- Consultas globais especiais.");
        System.out.println("\n0 - Sair");
    }
    
    public static void menuInterativas(){
        System.out.println("\nConsultas por Intervalo de Anos:\n");
        System.out.println("1 - O TOP X de número de publicações");
        System.out.println("2 - O TOP X de co-autorias");
        System.out.println("3 - A listagem de todos os co-autores comuns aos autores de uma dada lista de nomes a introduzida");
        System.out.println("4 - A listagem dos nomes dos autores que publicaram artigos em todos os anos do intervalo de anos dado");
        System.out.println("\n0 - Sair");
    }
    
    public static void topAutores(Anos anos, Integer anoi,Integer anof) {
        HashMap<String,Integer> finalAnos = new HashMap<String, Integer>(); //
        ValueComparator bvc =  new ValueComparator(finalAnos);
        TreeMap<String,Integer> ordenado = new TreeMap<String, Integer>(bvc);
        Scanner ler = new Scanner(System.in);
        int top;
                                
                                
        System.out.print("Numero para o TOP: ");
        top = ler.nextInt();
        HashMap<String,Integer> topPorAno = new HashMap<String, Integer>();
        Ano a;
        Autor autor;
        
        for (Integer i = anoi; i <= anof ; i++) {
            a = anos.getAno(i.toString());
            topPorAno = a.topArtigos();
            
            for(Map.Entry<String,Integer> entry : topPorAno.entrySet()) {
                String nome = entry.getKey();
                Integer artigos = entry.getValue();
                if (finalAnos.containsKey(nome)){
                    Integer fvalor = finalAnos.get(nome);
                    /*
                    if (fvalor < artigos) {
                        finalAnos.put(nome, artigos);
                    } else {
                        finalAnos.put(nome, fvalor);
                    }
                    */
                    fvalor+=artigos;
                    finalAnos.put(nome, fvalor);
                }else{
                    finalAnos.put(nome, artigos);
                }
            }
                
        }
        
        ordenado.putAll(finalAnos);
        
        /*
         * Imprime o TOP X
         */
        int k=1,topn=0;
        Integer value = 0;
        
        for(Map.Entry<String,Integer> entry : ordenado.entrySet()) {
                String key = entry.getKey();
                Integer val = value;
                value = entry.getValue();
                if (val == value) {
                } else {
                    topn++;
                }
                if (k<finalAnos.size()) System.out.println(topn + "º - " + value + " :: " + key);
                if (k==top){ break;}
                k++;
        }
                                    
    }
    
    public static void topCoautores(Anos anos,Integer anoi, Integer anof){
        HashMap<String,Integer> finalAnosC = new HashMap<String, Integer>(); //
        ValueComparator bvcC =  new ValueComparator(finalAnosC);
        TreeMap<String,Integer> ordenadoC = new TreeMap<String, Integer>(bvcC);
        HashMap<String,Integer> topPorAnoC = new HashMap<String, Integer>();
        Scanner ler = new Scanner(System.in);
        int top;
        
        System.out.print("Numero para o TOP: ");
        top = ler.nextInt();
        for (Integer i = anoi; i <= anof; i++) {
            Ano a = anos.getAno(i.toString());
            topPorAnoC = a.topCOArtigos();
            for(Map.Entry<String,Integer> entry : topPorAnoC.entrySet()) {
                String nome = entry.getKey();
                Integer artigos = entry.getValue();
                if(finalAnosC.containsKey(nome)){
                    Integer fvalor = finalAnosC.get(nome);
                    fvalor+=artigos;
                    finalAnosC.put(nome, fvalor);
                } else {
                    finalAnosC.put(nome, artigos);
                }
            }
        }
        
      
        ordenadoC.putAll(finalAnosC);
        
        int k=1,topn=0;
        Integer value = 0;
                                
        for(Map.Entry<String,Integer> entry : ordenadoC.entrySet()) {
            String key = entry.getKey();
            Integer val = value;
            value = entry.getValue();
            if (val == value) {
            
            } else {
            topn++;
            }
            String[] dados = key.split(",");
            if (k<finalAnosC.size()) System.out.println(topn + "º - " + value + " :: " + dados[0] + " , " + dados[1]);
            if (k==top){ break;}
            k++;
        }
        
    }
    
    public static void listaCoautoresComum(Anos anos,Integer anoi,Integer anof) {
        Scanner ler = new Scanner(System.in);
        String lista;
        ArrayList<String> coNomes = new ArrayList<String>();
        String[] nome;
        Ano ano;
        Autor autor = new Autor();
        TreeSet<String> coNomesFinal = new TreeSet<String>();
        
        System.out.print("Introduza a Lista de Autores (Separados por \",\"): ");
        lista = ler.nextLine();
        nome = trimAutorAno(lista);
        //Boolean flag;
        
        for (int i = 0; i < nome.length; i++) {
            for (Integer j = anoi; j <= anof; j++) {
                //System.out.println(j.toString());
                ano = anos.getAno(j.toString());
                //System.out.println(ano.toString());
                //System.out.println(nome[i]);
                if (ano.existeAutor(nome[i])){
                    autor = ano.getAutor(nome[i]);
                }
                coNomes = autor.listaCoautores();
                if(i == 0 && j.equals(anoi)) coNomesFinal.addAll(coNomes);
                for (String s : coNomesFinal) {
                    if (!coNomes.contains(s)){
                        coNomesFinal.remove(s);
                    }
                }
            }
        }
        
        
        
        /*
        for (Integer i = anoi; i < anof; i++) {
            ano = anos.getAno(i.toString());
            coNomes = ano.listaCoCom(nome);
            for (int j = 0; j < coNomes.size(); j++) {
                if(!coNomesFinal.contains(coNomes.get(j))){
                    coNomesFinal.add(coNomes.get(j));
                }
            }
        }
        */
        //System.out.println(nome.length);
        /*
        for (int i = 0; i < nome.length; i++) {
            for (Integer j = anoi; j <= anof; j++) {
                ano = anos.getAno(j.toString());
                //System.out.println("||||||||||||||||||"+nome[i].trim()+"||||");
                if (ano.existeAutor(nome[i].trim())) {
                    autor = ano.getAutor(nome[i].trim());
                }
                coNomes = autor.listaCoautores();
                if (coNomeFinal.isEmpty()){
                    coNomeFinal.addAll(coNomes);
                } else {
                    for (int k = 0; k < coNomeFinal.size(); k++) {
                        if (!coNomes.contains(coNomeFinal.get(k))){
                            coNomeFinal.remove(coNomeFinal.get(k));
                        }
                    }
                }
            }
        }
        */
        
        Iterator<String> autores = coNomesFinal.iterator();
        while(autores.hasNext()){
            System.out.println(autores.next());
        }
    }
    
    public static void listaAutoresInt(Anos anos,Integer anoi, Integer anof) {
        Ano a = anos.getAno(anoi.toString());
        Integer tamanho = a.numeroAutores();
        Integer anoM = anoi;
        for (Integer h=anoi+1; h<=anof; h++){
            a = anos.getAno(h.toString());
            if (tamanho > a.numeroAutores()){
                tamanho = a.numeroAutores();
                anoM = h;
            }
        }
        
        ArrayList<String> autoresT = new ArrayList<String>();
        a = anos.getAno(anoM.toString());
        autoresT = a.nomesAutores();
        
        for (Integer i=anoi; i<=anof; i++){
            if (i != anoM){
                a = anos.getAno(i.toString());
                ArrayList<String> autoresA = new ArrayList<String>();
                autoresA = a.nomesAutores();
                for (int j = 0; j < autoresT.size(); j++) {
                    if (!autoresA.contains(autoresT.get(j))){
                        //System.out.println("NÂO EXISTE");
                        autoresT.remove(autoresT.get(j));
                    }
                }
            }
        }
        for (int i = 0; i < autoresT.size(); i++) {
            System.out.println(autoresT.get(i));
        }    
    }
    
    public static void main(String[] args) {
        ArrayList<String> linhas;
        String[] dados;
        Anos anos = new Anos();
        Ano ano;
        Ano ano2;
        TreeMap<String,Integer> statsAno = new TreeMap<String, Integer>();
        Scanner ler = new Scanner(System.in);
        int op_menu;
        int flag=1;
        
        int n_nomes= 0;
        int n_artigos_umautor = 0;
        
        linhas = FileInput.txtInput();
        
        for (int i = 0; i < linhas.size(); i++) {
            dados = trimAutorAno(linhas.get(i));
            //System.out.println("|" + dados[dados.length-1].trim() + "|");
            if (dados.length > 2){
                for (int j = 0; j <= dados.length-2; j++) {
                    for (int k = 0; k <= dados.length-2; k++) {
                        if (j!=k) {
                            //System.out.println(dados[dados.length-1].trim()+"|"+dados[j].trim()+"|"+dados[k].trim());
                            anos.addAutorCoautor(dados[dados.length-1].trim(),dados[j].trim(), dados[k].trim());
                        }
                    }
                    //System.out.println("CO|"+dados[dados.length-1].trim()+"|");
                }
            } else {
                anos.addAutor(dados[dados.length-1].trim(), dados[0].trim());
                //System.out.println("AA|"+dados[dados.length-1].trim()+"|");
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
        
        while (flag != 0) {
            
            menuConsultasInteractivas();
        
            op_menu = ler.nextInt();
            
            switch (op_menu) {
                case 1:
                    Integer anoi,anof;
                    System.out.print("Introduza o ano inicial: ");
                    anoi = ler.nextInt();
                    System.out.print("Introduza o ano final: ");
                    anof = ler.nextInt();
                    
                    if (anoi <= anof){
                        menuInterativas();
                        op_menu = ler.nextInt();
                        switch (op_menu){
                            case 1:
                                topAutores(anos, anoi, anof);
                                break;
                            case 2:
                                topCoautores(anos, anoi, anof);
                                break;
                            case 3:
                                listaCoautoresComum(anos, anoi, anof);
                                break;
                            case 4:
                                if (anoi < anof) {
                                    listaAutoresInt(anos, anoi, anof);
                                } else {
                                    System.out.println("Intervalo Invalido!!");
                                }
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opção Invalida");
                        }
                    }
                    break;
                case 2:
                    
                    break;
                case 0:
                    flag = 0;
                    break;
                default:
                    System.out.println("Opção Invalida");
                    break;
            }
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
    ler.close();
    }
    
    
}


class ValueComparator implements Comparator<String> {

        Map<String, Integer> base;
        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
}


