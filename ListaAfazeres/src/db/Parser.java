package db;

import helper.DateHelper;
import model.Afazer;
import java.util.*;

public class Parser {

    public static List<Afazer> getOcorrencias(String conteudo) {
        ArrayList<Afazer> lista = new ArrayList<>();
        String[] objetos = conteudo.split("#");

        for (int i = 0; i < objetos.length; i++) {
            Afazer afazer = new Afazer();

            String objeto = objetos[i];
            String[] atributos = objeto.split(";");

            if (atributos.length <= 1) {
                break;
            }
            //System.out.println();

            String id = getAtributoPelaChave("id", atributos);
            String criado = getAtributoPelaChave("criado_em", atributos);
            String atualizado = getAtributoPelaChave("atualizado_em", atributos);

            afazer.setId(Integer.parseInt(id));
            afazer.setTitulo(getAtributoPelaChave("titulo", atributos));
            afazer.setConteudo(getAtributoPelaChave("conteudo", atributos));
            afazer.setCriadoEm(DateHelper.parse(criado, DateHelper.Formato.INPUT_DATE));
            afazer.setAtualizadoEm(DateHelper.parse(atualizado, DateHelper.Formato.INPUT_DATE));

            lista.add(afazer);
        }

        return lista;
    }

    private static String getAtributoPelaChave(String chave, String[] atributos) {
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