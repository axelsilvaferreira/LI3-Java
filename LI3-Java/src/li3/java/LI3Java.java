/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package li3.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Vector;



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
        System.out.println("2 - Consultas globais especiais.");
        System.out.println("\n3 - Gravar");
        System.out.println("4 - Carregar");
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
    
    public static void menuGerais(){
        System.out.println("\nConsultas Globais Especiais:\n");
        System.out.println("1 - Conta o numero de linhas em duplicado no ficheiro \"publicx.txt\"");
        System.out.println("2 - Tabela com todos os autores e respectiva rede de coautores, com numero de coautores inferior a X");
        System.out.println("\n0 - Sair");
    }
    
    public static void menuCarregar(){
        System.out.println("\nCarregar de uma das seguintes opções:\n");
        System.out.println("1 - Carregar dados de ficheiro um Ficheiro Texto");
        System.out.println("2 - Carregar dados de ficheiro um Ficheiro ObjectStream");
        System.out.println("\n0 - Sair");
    }
    public static void menuCarregarFicheiro(){
        System.out.println("\nCarregar de uma das seguintes opções:\n");
        System.out.println("1 - publicx");
        System.out.println("2 - Outro");
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
        HashMap<String,Integer> topPorAno;
        Ano a;
        Autor autor;
        
        for (Integer i = anoi; i <= anof ; i++) {
            //System.out.println("|"+i.toString()+"|");
            //System.out.println(anos.getAno(i.toString()));
            if (anos.existeAno(i.toString())){
                a = anos.getAno(i.toString());
                //System.out.println(a.toString());
                topPorAno = a.topArtigos();
            
                for(Map.Entry<String,Integer> entry : topPorAno.entrySet()) {
                    String nome = entry.getKey();
                    Integer artigos = entry.getValue();
                    if (finalAnos.containsKey(nome)){
                        Integer fvalor = finalAnos.get(nome);
                        
                        fvalor+=artigos;
                        finalAnos.put(nome, fvalor);
                    }else{
                        finalAnos.put(nome, artigos);
                    }
                }
            } else {
                System.out.println("O Ano "+i.toString()+" não existe!!");
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
                if (k<=finalAnos.size()) System.out.println(topn + "º - " + value + " :: " + key);
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
            if (anos.existeAno(i.toString())){
                Ano a = anos.getAno(i.toString());
                topPorAnoC = a.topCOArtigos();
                for(Map.Entry<String,Integer> entry : topPorAnoC.entrySet()) {
                    String nome = entry.getKey();
                    Integer artigos = entry.getValue();
                    String[] dados = nome.split(",");
                    String nomeAlt = dados[1]+","+dados[0];
                    if(finalAnosC.containsKey(nome)){
                        Integer fvalor = finalAnosC.get(nome);
                        //System.out.println("|"+nome+"|"+fvalor+"|");
                        fvalor+=artigos;
                        finalAnosC.put(nome, fvalor);
                    } else if (finalAnosC.containsKey(nomeAlt)){
                        Integer fvalor = finalAnosC.get(nomeAlt);
                        //System.out.println("|"+nomeAlt+"|"+fvalor+"|");
                        fvalor+=artigos;
                        finalAnosC.put(nomeAlt, fvalor);
                    } else {
                        //System.out.println("|"+nome+"|"+artigos+"|");
                        finalAnosC.put(nome, artigos);
                    }
                }
            } else {
                System.out.println("O Ano "+i.toString()+" não existe!!");
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
            if (k<=finalAnosC.size()) System.out.println(topn + "º - " + value + " :: " + dados[0] + " , " + dados[1]);
            if (k==top){ break;}
            k++;
        }
        
    }
    
    public static void listaCoautoresComum(Anos anos,Integer anoi,Integer anof) {
        Scanner ler = new Scanner(System.in);
        String lista;
        TreeSet<String> coNomes;
        String[] nome;
        Ano ano;
        Autor autor;
        TreeSet<String> coNomesFinal = new TreeSet<String>();
        TreeSet<String> coNomesFinalCOPIA = new TreeSet<String>();
        
        System.out.print("Introduza a Lista de Autores (Separados por \",\"): ");
        lista = ler.nextLine();
        nome = trimAutorAno(lista);
        boolean flag = false;
        //Boolean flag;
        for (int i = 0; i < nome.length; i++) {
            if(i==0){
                coNomes = anos.listaCoautoresDeAutorPorIntervalo(anoi, anof, nome[i].trim());
                coNomesFinal.addAll(coNomes);
            } else {
                coNomes = anos.listaCoautoresDeAutorPorIntervalo(anoi, anof, nome[i].trim());
                coNomesFinalCOPIA.clear();
                coNomesFinalCOPIA.addAll(coNomesFinal);
                for(String s: coNomesFinalCOPIA){
                    if (!coNomes.contains(s)) coNomesFinal.remove(s);
                }
            }    
        }
        
        Iterator<String> autores = coNomesFinal.iterator();
        while(autores.hasNext()){
            String n = autores.next();
            System.out.println(n);
        }
    }
    
    public static void listaAutoresInt(Anos anos,Integer anoi, Integer anof) {
        
        TreeSet<String> lista = new TreeSet<String>();
        TreeSet<String> listaT = new TreeSet<String>();
        TreeSet<String> listaTCOPIA = new TreeSet<String>();
        
        Ano ano;
        
        for (Integer i = anoi; i <= anof; i++) {
            if (i.equals(anoi)){
                ano = anos.getAno(i.toString());
                listaT.addAll(ano.listaAutores());
            } else {
                ano = anos.getAno(i.toString());
                lista = ano.listaAutores();
                listaTCOPIA.clear();
                listaTCOPIA.addAll(listaT);
                for(String s: listaTCOPIA){
                    if (!lista.contains(s)){
                        listaT.remove(s);
                    }
                }
            }
        }
        
        for (String s:listaT){
            System.out.println(s);
        }
    }
    
    public static void tabelaAutoresCoautores(Anos anos){
        HashMap<String,HashSet<String>> tabela = new HashMap<String, HashSet<String>>();
        HashSet<String> listaAnos = anos.listaAnos();
        HashSet<String> listaAutores = anos.listaAutoresComCoaurores();
        HashSet<String> l;
        Scanner ler = new Scanner(System.in);
        int numero;
        for(String a: listaAnos){
            Ano ano = anos.getAno(a);
            for(String au: listaAutores){
                l = new HashSet<String>();
                if (ano.existeAutor(au)){
                    Autor autor = ano.getAutor(au);
                    l.addAll(autor.listaCoautores());
                    tabela.put(au, l);
                }
            }
        }
        
        //System.out.println("Numero maximo de Coautores: ");
        //numero = ler.nextInt();
        
        for(Map.Entry<String,HashSet<String>> entry : tabela.entrySet()){
            String autor = entry.getKey();
            HashSet<String> coautores = entry.getValue();
            //System.out.println("||||||||||||||||"+coautores.size()+"||||||||||");
            if (coautores.size() > 0 && coautores.size() <= 200){
                //System.out.print(autor+": ");
                for(String s: coautores){
                    //if(!autor.equals(s)) System.out.print(s+", ");
                }
                //System.out.println();
            }
        }
    }
    
    public static void carrear(ArrayList<String> linhas){
        
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Vector<String> linhas;
        String[] dados;
        Anos anos = new Anos();
        Ano ano;
        Ano ano2;
        Autor autor;
        TreeMap<String,Integer> statsAno = new TreeMap<String, Integer>();
        Scanner ler = new Scanner(System.in);
        int op_menu;
        int flag=1;
        
        int n_nomes= 0;
        int n_artigos_umautor = 0;
        
        linhas = FileInput.txtInput();
        
        long inicio = System.nanoTime();
        for (int i = 0; i < linhas.size(); i++) {
            dados = trimAutorAno(linhas.get(i));
            anos.addAno(dados[dados.length-1].trim());
            n_nomes+=dados.length-1;
            
            if (statsAno.containsKey(dados[dados.length-1].trim())){
                int valor = statsAno.get(dados[dados.length-1].trim());
                valor+=1;
                statsAno.put(dados[dados.length-1].trim(), valor);
            } else {
                statsAno.put(dados[dados.length-1].trim(), 1);
            }
            //System.out.println("|"+dados[dados.length-1].trim()+"|");
            for (int j = 0; j <= dados.length-2; j++) {
                Ano a = anos.getAno(dados[dados.length-1].trim());
                a.addAutor(dados[j].trim());   
                //System.out.println("|"+dados[j].trim()+"|");
                if(dados.length == 2) {
                    n_artigos_umautor+=1;
                    autor = a.getAutor(dados[0].trim());
                    autor.setSolo(true);
                }
            }
            for (int j = 0; j <= dados.length-2; j++) {
                Ano a = anos.getAno(dados[dados.length-1].trim());
                Autor au = a.getAutor(dados[j].trim());
                for (int k = 0; k <= dados.length-2; k++) {
                    if(k!=j) au.addCoautor(dados[k].trim());
                }
            }
        }
        long fim = System.nanoTime();
        
        System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
        
        System.out.println("Informações do Ficheiro:\n");
        System.out.println("Nome do Ficheiro: " + FileInput.nomeFicheiro());
        System.out.println("Numero de Artigos: " + linhas.size());
        System.out.println("Numero de Nomes Lidos: " + n_nomes);
        System.out.println("Nuemro de Autores Diferentes: " + anos.numeroAutoresTotal());
        //System.out.println("Intervalo de anos: [" + anos.anoInicial() + " a " + anos.anoFinal() + "]");
        
        System.out.println("\nNumeros Gerais:\n");
        System.out.println("Numero Total de Autores: " + anos.numeroAutoresTotal());
        System.out.println("Numero de Arquivos com um unico Autor: " + n_artigos_umautor);
        System.out.println("Numero de Autores que SEMPRE Publicam a Solo: " + anos.numeroAutoresSemCO());
        System.out.println("Numero de Autores que NUNCA Publicam a Solo: " + anos.numeroAutoresComCO());
        
        System.out.println("\nNumero de Artigos por Ano:\n");
        inicio = System.nanoTime();
        for(Map.Entry<String,Integer> entry : statsAno.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            //System.out.println(key + " => " + value);
        }
        fim = System.nanoTime();
        
        System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
        
        while (flag != 0) {
            
            menuConsultasInteractivas();
        
            op_menu = ler.nextInt();
            
            switch (op_menu) {
                case 1:
                    Integer anoi,anof;
                    //System.out.print("Introduza o ano inicial: ");
                    //anoi = ler.nextInt();
                    //System.out.print("Introduza o ano final: ");
                    //anof = ler.nextInt();
                    anoi = 2005;
                    anof = 2013;
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
                                inicio = System.nanoTime();
                                listaCoautoresComum(anos, anoi, anof);
                                fim = System.nanoTime();
                                System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
                                break;
                            case 4:
                                if (anoi < anof) {
                                    inicio = System.nanoTime();
                                    listaAutoresInt(anos, anoi, anof);
                                    fim = System.nanoTime();
                                    System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
                                
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
                    menuGerais();
                    op_menu = ler.nextInt();
                    switch(op_menu){
                        case 1:
                            inicio = System.nanoTime();
                            ArrayList<String> linhas2;
                            linhas2 = FileInput.txtInputA();
                            HashSet<String> linhas2set = new HashSet<String>();
                            linhas2set.addAll(linhas2);
                            System.out.println("\nExistem "+(linhas.size()-linhas2set.size())+ " Linhas Duplicadas!");
                            fim = System.nanoTime();
                            System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
                            break;
                        case 2:
                            inicio = System.nanoTime();
                            tabelaAutoresCoautores(anos);
                            fim = System.nanoTime();
                            System.out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
                    }
                    break;
                case 3:
                    try{
                        FileOutputStream f = new FileOutputStream("publicx.obj");
                        ObjectOutputStream os = new ObjectOutputStream(f);
                        os.writeObject(anos);
                        os.close();
                        f.close();
                        System.out.println("\nGuardado!");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    menuCarregar();
                    op_menu = ler.nextInt();
                    switch(op_menu){
                        case 1:
                            menuCarregarFicheiro();
                            op_menu = ler.nextInt();
                            switch(op_menu) {
                                case 1:
                                    linhas = FileInput.txtInput();
                                    anos.limpar();
                                    for (int i = 0; i < linhas.size(); i++) {
                                        dados = trimAutorAno(linhas.get(i));
                                        anos.addAno(dados[dados.length-1].trim());
                                        n_nomes+=dados.length-1;
            
                                        if (statsAno.containsKey(dados[dados.length-1].trim())){
                                            int valor = statsAno.get(dados[dados.length-1].trim());
                                            valor+=1;
                                            statsAno.put(dados[dados.length-1].trim(), valor);
                                        } else {
                                            statsAno.put(dados[dados.length-1].trim(), 1);
                                        }
            
                                        for (int j = 0; j <= dados.length-2; j++) {
                                            Ano a = anos.getAno(dados[dados.length-1].trim());
                                            a.addAutor(dados[j].trim());   
                                            
                                            if(dados.length == 2) n_artigos_umautor+=1;
                                        }
                                        for (int j = 0; j <= dados.length-2; j++) {
                                            Ano a = anos.getAno(dados[dados.length-1].trim());
                                            Autor au = a.getAutor(dados[j].trim());
                                            for (int k = 0; k <= dados.length-2; k++) {
                                                if(k!=j) au.addCoautor(dados[k].trim());
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    String nome_ficheiro;
                                    System.out.print("\nIntroduza o nome do ficheiro: ");
                                    nome_ficheiro = ler.next();
                                    linhas = FileInput.txtInput(nome_ficheiro);
                                    anos.limpar();
                                    for (int i = 0; i < linhas.size(); i++) {
                                        dados = trimAutorAno(linhas.get(i));
                                        anos.addAno(dados[dados.length-1].trim());
                                        n_nomes+=dados.length-1;
            
                                        if (statsAno.containsKey(dados[dados.length-1].trim())){
                                            int valor = statsAno.get(dados[dados.length-1].trim());
                                            valor+=1;
                                            statsAno.put(dados[dados.length-1].trim(), valor);
                                        } else {
                                            statsAno.put(dados[dados.length-1].trim(), 1);
                                        }
            
                                        for (int j = 0; j <= dados.length-2; j++) {
                                            Ano a = anos.getAno(dados[dados.length-1].trim());
                                            a.addAutor(dados[j].trim());   
                                            
                                            if(dados.length == 2) n_artigos_umautor+=1;
                                        }
                                        for (int j = 0; j <= dados.length-2; j++) {
                                            Ano a = anos.getAno(dados[dados.length-1].trim());
                                            Autor au = a.getAutor(dados[j].trim());
                                            for (int k = 0; k <= dados.length-2; k++) {
                                                if(k!=j) au.addCoautor(dados[k].trim());
                                            }
                                        }
                                    }
                                    break;
                            }
                            break;
                        case 2:
                            menuCarregarFicheiro();
                            op_menu = ler.nextInt();
                            switch(op_menu){
                                case 1:
                                    try{
                                        FileInputStream f = new FileInputStream("publicx.obj");
                                        ObjectInputStream is = new ObjectInputStream(f);
                                        anos = (Anos) is.readObject();
                                        is.close();
                                        f.close();
                                        System.out.println("\nCarregado!");
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    String nome_ficheiro;
                                    System.out.print("\nIntroduza o nome do ficheiro: ");
                                    nome_ficheiro = ler.next();
                                    try{
                                        FileInputStream f = new FileInputStream(nome_ficheiro);
                                        ObjectInputStream is = new ObjectInputStream(f);
                                        anos = (Anos) is.readObject();
                                        is.close();
                                        f.close();
                                        System.out.println("\nCarregado!");
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                            }
                    }
                    break;
                case 0:
                    flag = 0;
                    break;
                default:
                    System.out.println("Opção Invalida");
                    break;
            }
        }
        
        
    ler.close();
    }
    
    
}


class ValueComparator implements Comparator<String> {

        Map<String, Integer> base;
        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }
        
        @Override
        public int compare(String a, String b) {
            if (base.get(a) > base.get(b)) {
                return -1;
            } else if (base.get(a) < base.get(b)){
                return 1;
            } else {
                return a.compareTo(b);
            }
        }
}


