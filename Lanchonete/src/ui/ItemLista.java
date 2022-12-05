package ui;

import model.Produto;
import java.awt.*;
import java.awt.event.*;

public class ItemLista {
    private Produto produto;
    private Checkbox checkbox;
    private Label lbField;
    private TextField textField;

    public ItemLista(Produto produto, int y) {
        this.produto = produto;
        configurarAtributos(y);
    }

    private void configurarAtributos(int y) {
        String nome = produto.getNome();
        double valor = produto.getPreco();
        String texto = String.format("%s  R$ %.2f", nome, valor);

        checkbox = new Checkbox(texto);
        checkbox.setBounds(40, y, 200, 30);

        lbField = new Label("Quantidade");
        lbField.setBounds(checkbox.getX() + checkbox.getWidth() + 10, checkbox.getY(), 75, 30);
        lbField.setVisible(false);

        textField = new TextField();
        textField.setBounds(lbField.getX() + lbField.getWidth(), lbField.getY() + 5, 40, 20);
        textField.setVisible(false);
    }

    public Produto getProduto() {
        return produto;
    }

    public Checkbox getCheckbox() {
        return checkbox;
    }

    public Label getLabel() {
        return lbField;
    }

    public TextField getTextField() {
        return textField;
    }

}