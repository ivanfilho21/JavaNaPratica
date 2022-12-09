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
    private JButton btnDuplicar = new JButton();

    public PainelTopo(Contexto contexto) {
        this.contexto = contexto;
        setLayout(new GridLayout(6, 1));
        setBackground(RColors.ROSA_CLARO);

        configurarBotao(btnAdd, "Criar", "address_book_pad", true);
        configurarBotao(btnMarcarTodos, "Marcar Todos", "wia_img_check-0", true);
        configurarBotao(btnExcluir, "Excluir", "recycle_bin_file", false);
        configurarBotao(btnDuplicar, "Duplicar", "address_book_card_copy-0", false);

        add(btnAdd);
        add(btnMarcarTodos);
        add(btnDuplicar);
        add(btnExcluir);
    }

    public JButton getBotaoAdicionar() {
        return btnAdd;
    }

    public JButton getBotaoDuplicar() {
        return btnDuplicar;
    }

    public JButton getBotaoExcluir() {
        return btnExcluir;
    }

    public JButton getBotaoMarcarTodos() {
        return btnMarcarTodos;
    }

    public void habilitarBotoesDeItem(int selecionados) {
        btnExcluir.setEnabled(selecionados > 0);
        btnDuplicar.setEnabled(selecionados == 1);
    }

    private void configurarBotao(JButton botao, String texto, String imagem, boolean habilitado) {
        URL imgURL = contexto.getImageAsset(imagem + ".png");

        if (imgURL != null) {
            botao.setIcon(new ImageIcon(imgURL));
            botao.setVerticalTextPosition(AbstractButton.BOTTOM);
            botao.setHorizontalTextPosition(AbstractButton.CENTER);
        }
        botao.setEnabled(habilitado);
        botao.setText(texto);
        botao.setBackground(RColors.CINZA_CLARO);
    }

}