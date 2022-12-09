package ui.tela_inicio;

import java.awt.*;
import javax.swing.*;
import helper.DateHelper;
import model.Afazer;

public class ItemLista extends Panel {
    private Afazer afazer;
    private JCheckBox checkbox;
    private JLabel lbTitulo;
    private JLabel lbConteudo;
    private JLabel lbCriadoEm;
    private JLabel lbAtualizadaEm;

    public ItemLista(Afazer afazer) {
        this.afazer = afazer;

        setLayout(new GridLayout(1, 4));
        montar();
    }

    private void montar() {
        String criadoEm = DateHelper.format(afazer.getCriadoEm(), DateHelper.Formato.OUTPUT_DATE, "-");
        String atualizadoEm = DateHelper.format(afazer.getAtualizadoEm(), DateHelper.Formato.OUTPUT_DATE, "-");

        checkbox = new JCheckBox("" + afazer.getId());
        checkbox.setBackground(null);
        
        lbTitulo = new JLabel(afazer.getTitulo());
        lbConteudo = new JLabel(afazer.getConteudo());
        lbCriadoEm = new JLabel(criadoEm);
        lbAtualizadaEm = new JLabel(atualizadoEm);

        add(checkbox);
        add(lbTitulo);
        add(lbCriadoEm);
        add(lbAtualizadaEm);
    }

    public Afazer getAfazer() {
        return afazer;
    }

    public JCheckBox getCheckBox() {
        return checkbox;
    }

}