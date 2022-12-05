package principal;

import db.BancoDeDados;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import ui.tela_inicio.TelaInicial;

public class Afazeres extends Contexto {
    private Frame janela = new Frame();

    public Afazeres() {
        BancoDeDados.criarInstancia(this);

        janela.setTitle("Lista de Afazeres");
        janela.setSize(600, 460);
        //janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                janela.dispose();
                System.exit(1);
            }
        });

        inicializarComFrame(janela);
        iniciarTela(new TelaInicial(this));

        janela.setVisible(true);
    }

}