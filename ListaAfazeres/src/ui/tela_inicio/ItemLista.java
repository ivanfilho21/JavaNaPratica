package ui.tela_inicio;

import java.awt.*;
import helper.DateHelper;
import model.Afazer;

public class ItemLista extends Panel {
    private Afazer afazer;
    private Checkbox checkbox;
    private Label lbId;
    private Label lbTitulo;
    private Label lbConteudo;
    private Label lbCriadoEm;
    private Label lbAtualizadaEm;

    public ItemLista(Afazer afazer) {
        this.afazer = afazer;

        setLayout(new GridLayout(1, 5));
        montar();
    }

    public Afazer getAfazer() {
        return afazer;
    }

    private void montar() {
        String criadoEm = DateHelper.format(afazer.getCriadoEm(), DateHelper.Formato.OUTPUT_DATE, "-");
        String atualizadoEm = DateHelper.format(afazer.getAtualizadoEm(), DateHelper.Formato.OUTPUT_DATE, "-");

        checkbox = new Checkbox();
        lbId = new Label("" + afazer.getId());
        lbTitulo = new Label(afazer.getTitulo());
        lbConteudo = new Label(afazer.getConteudo());
        lbCriadoEm = new Label(criadoEm);
        lbAtualizadaEm = new Label(atualizadoEm);

        add(checkbox);
        add(lbId);
        add(lbTitulo);
        add(lbConteudo);
        add(lbCriadoEm);
        add(lbAtualizadaEm);
    }

}