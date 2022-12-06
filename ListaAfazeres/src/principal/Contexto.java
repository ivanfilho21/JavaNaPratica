package principal;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import ui.TelaBase;

public abstract class Contexto {
    public static final String FILE_SEPARATOR = File.separator;
    private final String ASSETS_PATH = "assets" + FILE_SEPARATOR;
    private final String ASSETS_IMG_PATH = ASSETS_PATH + "img" + FILE_SEPARATOR;

    private Frame frame = null;
    private Stack<TelaBase> pilhaDeTelas = new Stack<>();
    private String nomeClasseQueRecebeResultado = null;
    private int resultadoDaTela = 0;

    protected void inicializarComFrame(Frame frame) {
        this.frame = frame;
    }
    
    public void iniciarTela(TelaBase tela) {
        pilhaDeTelas.push(tela);
        mostrarTelaAtual();
    }

    public void iniciarTelaComResultado(TelaBase tela) {
        nomeClasseQueRecebeResultado = pilhaDeTelas.peek().getClass().getSimpleName();
        iniciarTela(tela);
    }

    public void removerTela() {
        resultadoDaTela = pilhaDeTelas.peek().getResultado();
        pilhaDeTelas.pop();
        mostrarTelaAtual();
    }

    public String getAssets(String nome) {
        File file = new File(ASSETS_PATH + nome);

        //System.out.println("Caminho do Asset: " + ASSETS_PATH + nome);
        //System.out.println("Arquivo existe: " + file.exists());

        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            fr = new FileReader(file);
        } catch (Exception e) {
            //
        }

        if (fr != null) {
            br = new BufferedReader(fr);
        }

        if (br != null) {
            try {
                while (br.ready()) {
                    sb.append(br.readLine());
                }
            } catch (Exception e) {
                //
            }
        }

        return sb.toString().trim();
    }

    public URL getImageAsset(String nome) {
        File file = new File(ASSETS_IMG_PATH + nome);

        System.out.println("Caminho do Asset: " + ASSETS_IMG_PATH + nome);
        System.out.println("Arquivo existe: " + file.exists());

        try {
            return file.toURI().toURL();
        } catch (Exception e) {
            return null;
        }
    }

    public void appendAssetsFile(String nome, String conteudo) {
        File file = new File(ASSETS_PATH + nome);
        
        //
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file, true);
        } catch(Exception e) {
            //
        }

        if (fw != null) {
            bw = new BufferedWriter(fw);
        }

        if (bw != null) {
            try {
                bw.write(conteudo);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println("Não foi possível ler arquivo.");
                e.printStackTrace();
            }
        }
    }

    private void mostrarTelaAtual() {
        TelaBase tela = pilhaDeTelas.peek();

        if (frame != null && tela != null) {
            frame.removeAll();
            frame.add(tela);
        }
        
        if (!tela.isCriada()) {
            tela.criar();
        }

        tela.resumir();

        if (nomeClasseQueRecebeResultado != null && nomeClasseQueRecebeResultado.equals(tela.getClass().getSimpleName())) {
            tela.receberResultado(resultadoDaTela);
        }
        
        frame.revalidate();
    }

}