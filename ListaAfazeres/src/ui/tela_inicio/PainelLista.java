package ui.tela_inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import model.Afazer;
import resources.*;
import ui.tela_inicio.listener.*;

public class PainelLista extends Panel {
    private List<Afazer> lista = new ArrayList<>();
    private ArrayList<ItemLista> listaItens = new ArrayList<>();
    private AoSelecionarListener selecionarListener = null;
    private ListaVaziaListener listaVaziaListener = null;

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

        boolean listaVazia = lista.isEmpty();

        if (listaVaziaListener != null) {
            listaVaziaListener.listaVazia(listaVazia);
        }
        
        if (listaVazia) {
            montarTelaVazia();
        } else {
            montarLabels();

            for (int i = 0; i < lista.size(); i++) {
                final ItemLista item = new ItemLista(lista.get(i));
                item.getCheckBox().addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (selecionarListener != null) {
                            selecionarListener.aoSelecionar(getItensSelecionados());
                        }
                    }
                });
                listaItens.add(item);
                add(item);
            }
        }

        revalidate();
    }

    private void montarLabels() {
        Panel linha = new Panel(new GridLayout(1, 4));
        JLabel lbId = new JLabel("ID");
        lbId.setHorizontalAlignment(SwingConstants.CENTER);
        linha.add(lbId);
        linha.add(new JLabel("Titulo"));
        linha.add(new JLabel("Criado em"));
        linha.add(new JLabel("Atualizado em"));
        add(linha);
    }

    private void montarTelaVazia() {
        add(new JLabel("Não há anotações"));
    }

    private boolean todosSelecionados() {
        for (int i = 0; i < listaItens.size(); i++) {
            if (!listaItens.get(i).getCheckBox().isSelected()) {
                return false;
            }
        }

        return true;
    }

    public ItemLista getItemSelecionado() {
        for (int i = 0; i < listaItens.size(); i++) {
            ItemLista item = listaItens.get(i);

            if (item.getCheckBox().isSelected()) {
                return item;
            }
        }

        return null;
    }

    public List<ItemLista> getItensSelecionados() {
        ArrayList<ItemLista> listaSelecionados = new ArrayList<>();

        for (int i = 0; i < listaItens.size(); i++) {
            ItemLista item = listaItens.get(i);

            if (item.getCheckBox().isSelected()) {
                listaSelecionados.add(item);
            }
        }

        return listaSelecionados;
    }

    public void marcarTodos() {
        boolean marcados = todosSelecionados();

        for (int i = 0; i < listaItens.size(); i++) {
            listaItens.get(i).getCheckBox().setSelected(!marcados);
        }
    }

    public void setAoSelecionarListener(AoSelecionarListener listener) {
        this.selecionarListener = listener;
    }

    public void setListaVaziaListener(ListaVaziaListener listener) {
        this.listaVaziaListener = listener;
    }

}