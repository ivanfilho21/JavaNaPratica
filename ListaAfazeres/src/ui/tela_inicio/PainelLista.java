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
            final ItemLista item = new ItemLista(lista.get(i));
            item.getCheckBox().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (selecionarListener != null) {
                        selecionarListener.aoSelecionar(item);
                    }
                }
            });
            listaItens.add(item);
            add(item);
        }
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
        //
    }

    private boolean todosSelecionados() {
        for (int i = 0; i < listaItens.size(); i++) {
            if (!listaItens.get(i).getCheckBox().isSelected()) {
                return false;
            }
        }

        return true;
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

}