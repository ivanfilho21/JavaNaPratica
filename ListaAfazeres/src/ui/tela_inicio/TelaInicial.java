package ui.tela_inicio;

import db.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import model.Afazer;
import principal.Contexto;
import ui.TelaBase;
import ui.tela_adicionar.TelaAdicionar;
import ui.tela_inicio.listener.*;
import ui.tela_inicio.listener.*;

public class TelaInicial extends TelaBase {
    private PainelTopo painelTopo;
    private PainelLista painelLista;

    public TelaInicial(Contexto contexto) {
        super(contexto);
        painelTopo = new PainelTopo(contexto);
        painelLista = new PainelLista();
    }

    @Override
    public void aoCriar() {
        add(painelTopo, BorderLayout.LINE_START);
        add(painelLista, BorderLayout.CENTER);

        painelTopo.getBotaoAdicionar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTelaComResultado(new TelaAdicionar(getContexto()));
            }
        });

        painelTopo.getBotaoDuplicar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemLista item = painelLista.getItemSelecionado();
                if (item != null) {
                    BancoDeDados.getInstancia().adicionarAfazer(item.getAfazer());
                    carregarLista();
                }
            }
        });

        painelTopo.getBotaoExcluir().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<ItemLista> selecionados = painelLista.getItensSelecionados();
                
                for (int i = 0; i < selecionados.size(); i++) {
                    ItemLista item = selecionados.get(i);
                    BancoDeDados.getInstancia().excluirAfazer(item.getAfazer());
                }

                carregarLista();
            }
        });

        painelTopo.getBotaoMarcarTodos().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelLista.marcarTodos();
            }
        });

        painelLista.setAoSelecionarListener(new AoSelecionarListener() {
            public void aoSelecionar(List<ItemLista> itens) {
                painelTopo.habilitarBotoesDeItem(itens.size());
            }
        });

        painelLista.setListaVaziaListener(new ListaVaziaListener() {
            public void listaVazia(boolean vazia) {
                painelTopo.getBotaoMarcarTodos().setEnabled(!vazia);
            }
        });

        
        carregarLista();
    }

    @Override
    public void aoResumir() {
        //
    }

    @Override
    public void aoReceberResultado(int resultado) {
        if (resultado == TelaBase.RESULTADO_TELA_OK) {
            carregarLista();
        }
    }

    private void carregarLista() {
        List<Afazer> lista = BancoDeDados.getInstancia().listarAfazeres();
        painelLista.setLista(filtrarItensNaoDeletados(lista));
    }

    private List<Afazer> filtrarItensNaoDeletados(List<Afazer> lista) {
        ArrayList<Afazer> filtrada = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Afazer afazer = lista.get(i);

            if (!afazer.isDeletado()) {
                filtrada.add(afazer);
            }
        }

        return filtrada;
    }

}