package db;

import java.util.*;
import helper.DateHelper;
import model.Afazer;
import principal.Contexto;

public class BancoDeDados {
    private Contexto contexto;
    private final String nomeArquivo = "afazeres.txt";
    private static BancoDeDados instancia = null;

    private BancoDeDados(Contexto contexto) {
        this.contexto = contexto;
    }

    public static void criarInstancia(Contexto contexto) {
        if (instancia == null) {
            instancia = new BancoDeDados(contexto);
        }
    }

    public static BancoDeDados getInstancia() {
        return instancia;
    }

    public List<Afazer> listarAfazeres() {
        String conteudoArquivo = contexto.getAssets(nomeArquivo);
        //System.out.println("Conteudo: " + conteudoArquivo);

        return Parser.getOcorrencias(conteudoArquivo);
    }

    public void adicionarAfazer(Afazer afazer) {
        int tamanho = listarAfazeres().size();
        String criadoEm = DateHelper.format(new Date(), DateHelper.Formato.INPUT_DATE, null);

        afazer.setId(tamanho + 1);
        
        String linha = new StringBuilder()
                .append("id:").append(afazer.getId()).append(";")
                .append("criado_em:").append(criadoEm).append(";")
                .append("atualizado_em:").append(";")
                .append("titulo:").append(afazer.getTitulo()).append(";")
                .append("conteudo:").append(afazer.getConteudo()).append(";")
                .append("#")
                .toString();

        contexto.appendAssetsFile(nomeArquivo, linha);
    }

}