package main;

import helper.TypeHelper;
import model.Produto;
import ui.ItemLista;
import ui.Janela;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Lanchonete {
    private Janela frame;
    private Button btnAction;
    private ArrayList<ItemLista> itens = new ArrayList();
    private ItemListener produtoItemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            Checkbox cbAtual = (Checkbox) e.getItemSelectable();
            ItemLista itemAtual = null;

            for (int i = 0; i < itens.size(); i++) {
                ItemLista item = itens.get(i);

                if (item.getCheckbox() == cbAtual) {
                    itemAtual = item;
                    break;
                }
            }
            
            if (itemAtual != null) {
                boolean estado = cbAtual.getState();
                itemAtual.getLabel().setVisible(estado);
                itemAtual.getTextField().setVisible(estado);
            }

            validarBotao();
        }
    };
    private ArrayList<Produto> lanches = new ArrayList();
    private ArrayList<Produto> bebidas = new ArrayList();
    private int marginTop = 30;

    public Lanchonete() {
        frame = new Janela(400, 400, "Lanchonete");

        configurarProdutos();
        configurarMenuPrincipal();
    }

    private void configurarProdutos() {
        lanches.add(new Produto("Cachorro Quente", 5.0, 3));
        lanches.add(new Produto("Sanduiche de Presunto", 3.49, 7));
        lanches.add(new Produto("Enroladinho de Queijo", 2.3, 11));
        lanches.add(new Produto("Hamburguer de Salada", 9.99, 5));

        bebidas.add(new Produto("Suco Natural", 6.5, 10));
        bebidas.add(new Produto("Refrigerante", 3.5, 20));
        bebidas.add(new Produto("Milkshake", 12.0, 5));
    }

    private void configurarMenuPrincipal() {
        Label title = new Label("Lanchonete");
        Label lbLanches = new Label("Lanches");
        Label lbBebidas = new Label("Bebidas");

        int x = 20;
        int y = 40;
        int w = 100;
        int h = 30;

        title.setBounds(frame.getWidth()/2 - w/2, y, w, h);

        y = title.getY() + marginTop;
        lbLanches.setBounds(x, y, w, h);

        frame.add(title);
        frame.add(lbLanches);

        y = lbLanches.getY() + marginTop;
        y = desenharListaProdutos(lanches, y);

        lbBebidas.setBounds(x, y, w, h);
        frame.add(lbBebidas);

        y = lbBebidas.getY() + marginTop;
        y = desenharListaProdutos(bebidas, y);

        desenharBotao();
    }

    private int desenharListaProdutos(List<Produto> lista, int y) {
        for (int i = 0; i < lista.size(); i++) {
            y = desenharProduto(lista.get(i), y);
        }

        return y;
    }

    private int desenharProduto(Produto produto, int y) {
        ItemLista item = new ItemLista(produto, y);
        Checkbox cb = item.getCheckbox();
        cb.addItemListener(produtoItemListener);
        y = cb.getY() + marginTop;

        item.getTextField().addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                validarBotao();
            }
        });
        itens.add(item);

        frame.add(item.getCheckbox());
        frame.add(item.getLabel());
        frame.add(item.getTextField());

        return y;
    }

    private void desenharBotao() {
        int btnWidth = 100;
        int btnHeight = 30;
        btnAction = new Button("Fechar Pedido");

        btnAction.setBounds(
            frame.getWidth()/2 - btnWidth/2,
            frame.getHeight() - (btnHeight + 20),
            btnWidth,
            btnHeight
        );
        btnAction.setEnabled(false);
        btnAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completarVenda();
            }
        });

        frame.add(btnAction);
    }

    private void validarBotao() {
        boolean peloMenosUmMarcado = false;

        for (int i = 0; i < itens.size(); i++) {
            ItemLista item = itens.get(i);

            if (item.getCheckbox().getState()) {
                peloMenosUmMarcado = true;
                break;
            }
        }

        boolean todasAsQtdValidas = true;

        for (int i = 0; i < itens.size(); i++) {
            ItemLista item = itens.get(i);

            // Se não estiver marcado o Checkbox, passa direto pro próximo item
            if (!item.getCheckbox().getState()) {
                continue;
            }

            String qtdTexto = item.getTextField().getText();
            boolean qtdPreenchida = !qtdTexto.isEmpty();
            boolean qtdValida = TypeHelper.parseInt(qtdTexto) > 0;

            todasAsQtdValidas = todasAsQtdValidas && qtdPreenchida && qtdValida;
        }

        btnAction.setEnabled(peloMenosUmMarcado && todasAsQtdValidas);
    }

    private void completarVenda() {
        double valorTotal = 0.0;

        for (int i = 0; i < itens.size(); i++) {
            ItemLista item = itens.get(i);
            double qtd = TypeHelper.parseInt(item.getTextField().getText());

            valorTotal += item.getProduto().getPreco() * qtd;
        }

        String texto = String.format("Valor total do Pedido: R$ %.2f", valorTotal);
        JOptionPane.showMessageDialog(null, texto);
        System.exit(0);
    }

}