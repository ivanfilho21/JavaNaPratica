package ui.tela_inicio;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.Afazer;
import resources.*;

public class PainelLista extends Panel {
    private List<Afazer> lista = new ArrayList<>();
    private ArrayList<ItemLista> listaItens = new ArrayList<>();

    public PainelLista() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
        gridLayout.setRows(10);
        setLayout(gridLayout);
        setBackground(RColors.AZUL_CLARO);
    }

    public void setLista(List<Afazer> lista) {
        this.lista = lista;
        atualizarTela();
    }

    private void atualizarTela() {
        removeAll();
        revalidate();
        
        if (lista.isEmpty()) {
            montarTelaVazia();
            return;
        }

        montarLabels();

        for (int i = 0; i < lista.size(); i++) {
            ItemLista item = new ItemLista(lista.get(i));
            listaItens.add(item);
            add(item);
        }
    }

    private void montarLabels() {
        Panel linha = new Panel(new GridLayout(1, 5));
        linha.add(new Label("Selecionar"));
        linha.add(new Label("ID"));
        linha.add(new Label("Titulo"));
        linha.add(new Label("Conteudo"));
        linha.add(new Label("Criado em"));
        linha.add(new Label("Atualizado em"));
        add(linha);
    }

    private void montarTelaVazia() {
        //
    }

}