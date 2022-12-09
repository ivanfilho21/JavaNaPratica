package db;

import helper.DateHelper;
import java.util.*;
import model.Afazer;
import principal.Contexto;

public class BancoDeDados {
    private Contexto contexto;
    private final String ARQUIVO = "afazeres.txt";
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
        String conteudoArquivo = contexto.getAssets(ARQUIVO);
        //System.out.println("Conteudo: " + conteudoArquivo);

        return Parser.getOcorrencias(conteudoArquivo);
    }

    public void adicionarAfazer(Afazer afazer) {
        afazer.setId(listarAfazeres().size() + 1);
        afazer.setCriadoEm(new Date());
        afazer.setAtualizadoEm(new Date());

        contexto.updateAssetsFile(ARQUIVO, montarLinha(afazer), true);
    }

    public void excluirAfazer(Afazer afazer) {
        String conteudoArquivo = contexto.getAssets(ARQUIVO);
        String id = "" + afazer.getId();
        afazer.setAtualizadoEm(new Date());
        afazer.setDeletado(true);

        String novoConteudo = Parser.atualizarPeloId(conteudoArquivo, id, montarLinha(afazer));

        contexto.updateAssetsFile(ARQUIVO, novoConteudo, false);
    }

    private String montarLinha(Afazer afazer) {
        String criadoEm = DateHelper.format(afazer.getCriadoEm(), DateHelper.Formato.INPUT_DATE, null);
        String atualizadoEm = DateHelper.format(afazer.getAtualizadoEm(), DateHelper.Formato.INPUT_DATE, null);
        String titulo = afazer.getTitulo();
        String conteudo = afazer.getConteudo();
        String deletado = afazer.isDeletado() ? "S" : "";

        return new StringBuilder()
                .append("id:").append("" + afazer.getId()).append(";")
                .append("criado_em:").append(criadoEm).append(";")
                .append("atualizado_em:").append(atualizadoEm).append(";")
                .append("titulo:").append(titulo == null ? "" : titulo).append(";")
                .append("conteudo:").append(conteudo == null ? "" : conteudo).append(";")
                .append("deletado:").append(deletado).append(";")
                .append("#")
                .toString();
    }

}