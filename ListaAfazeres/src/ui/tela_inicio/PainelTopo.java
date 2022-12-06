package ui.tela_inicio;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import principal.Contexto;
import resources.*;

public class PainelTopo extends Panel {
    private Contexto contexto;
    private JButton btnAdd = new JButton();
    private JButton btnExcluir = new JButton();
    private JButton btnMarcarTodos = new JButton();

    public PainelTopo(Contexto contexto) {
        this.contexto = contexto;
        setLayout(new GridLayout(6, 1));
        setBackground(RColors.ROSA_CLARO);

        configurarBotao(btnAdd, "Criar", "address_book_pad");
        configurarBotao(btnExcluir, "Excluir", "recycle_bin_file");
        configurarBotao(btnMarcarTodos, "Marcar Todos", "wia_img_check-0");

        add(btnAdd);
        add(btnMarcarTodos);
        add(btnExcluir);
    }

    public JButton getBotaoAdicionar() {
        return btnAdd;
    }

    public JButton getBotaoExcluir() {
        return btnExcluir;
    }

    public JButton getBotaoMarcarTodos() {
        return btnMarcarTodos;
    }

    private void configurarBotao(JButton botao, String texto, String imagem) {
        URL imgURL = contexto.getImageAsset(imagem + ".png");

        if (imgURL != null) {
            botao.setIcon(new ImageIcon(imgURL));
            botao.setVerticalTextPosition(AbstractButton.BOTTOM);
            botao.setHorizontalTextPosition(AbstractButton.CENTER);
        }

        botao.setText(texto);
        botao.setBackground(RColors.CINZA_CLARO);
    }

}