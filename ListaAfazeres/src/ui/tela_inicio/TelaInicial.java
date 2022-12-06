package ui.tela_inicio;

import db.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import model.Afazer;
import principal.Contexto;
import ui.TelaBase;
import ui.tela_adicionar.TelaAdicionar;

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

        carregarLista();

        painelTopo.getBotaoAdicionar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTelaComResultado(new TelaAdicionar(getContexto()));
            }
        });

        painelTopo.getBotaoExcluir().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        painelTopo.getBotaoMarcarTodos().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
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
        System.out.println("\nQtd de afazeres: " + lista.size());

        painelLista.setLista(lista);
    }

}