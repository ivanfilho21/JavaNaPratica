package ui.tela_inicio;

import java.awt.*;
import resources.*;

public class PainelTopo extends Panel {
    private Button btnAdd = new Button("Criar");
    private Button btnEditar = new Button("Editar");

    public PainelTopo() {
        setBackground(RColors.ROSA_CLARO);

        add(btnAdd);
        add(btnEditar);
    }

    public Button getBotaoAdicionar() {
        return btnAdd;
    }

    public Button getBotaoEditar() {
        return btnEditar;
    }

}