package db;

import helper.DateHelper;
import model.Afazer;
import java.util.*;

public class Parser {

    public static String atualizarPeloId(String conteudo, String id, String conteudoLinha) {
        ArrayList<Afazer> lista = new ArrayList<>();
        String[] objetos = conteudo.split("#");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < objetos.length; i++) {
            String objeto = objetos[i];
            String[] atributos = objeto.split(";");

            if (atributos.length <= 1) {
                continue;
            }

            String objId = getValorDoAtributoPelaChave("id", atributos);

            if (id.equals(objId)) {
                sb.append(conteudoLinha).append("#");
                continue;
            }

            sb.append(objeto).append("#");
        }

        return sb.toString();
    }

    public static List<Afazer> getOcorrencias(String conteudo) {
        ArrayList<Afazer> lista = new ArrayList<>();
        String[] objetos = conteudo.split("#");

        for (int i = 0; i < objetos.length; i++) {
            Afazer afazer = new Afazer();

            String objeto = objetos[i];
            String[] atributos = objeto.split(";");

            if (atributos.length <= 1) {
                continue;
            }
            //System.out.println();

            String id = getValorDoAtributoPelaChave("id", atributos);
            String criado = getValorDoAtributoPelaChave("criado_em", atributos);
            String atualizado = getValorDoAtributoPelaChave("atualizado_em", atributos);
            String deletado = getValorDoAtributoPelaChave("deletado", atributos);

            afazer.setId(Integer.parseInt(id));
            afazer.setTitulo(getValorDoAtributoPelaChave("titulo", atributos));
            afazer.setConteudo(getValorDoAtributoPelaChave("conteudo", atributos));
            afazer.setDeletado(deletado != null && deletado.equalsIgnoreCase("S"));
            afazer.setCriadoEm(DateHelper.parse(criado, DateHelper.Formato.INPUT_DATE));
            afazer.setAtualizadoEm(DateHelper.parse(atualizado, DateHelper.Formato.INPUT_DATE));

            lista.add(afazer);
        }

        return lista;
    }

    private static String getValorDoAtributoPelaChave(String chave, String[] atributos) {
        for (int i = 0; i < atributos.length; i++) {
            String atributoTexto = atributos[i];
            String[] chaveValor = atributoTexto.split(":");
            String atributoNome = chaveValor.length > 0 ? chaveValor[0] : null;
            String atributoValor = chaveValor.length > 1 ? chaveValor[1] : null;

            if (atributoNome.equals(chave)) {
                //System.out.printf("[%s: %s] ", atributoNome, atributoValor);
                return atributoValor;
            }
        }

        return null;
    }

}