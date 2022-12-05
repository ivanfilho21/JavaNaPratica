package ui;

import java.awt.*;
import java.awt.event.*;

public class Janela extends Frame {
    public Janela(int largura, int altura, String titulo) {
        setTitle(titulo);
        setSize(largura, altura);
        setLayout(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(1);
            }
        });
    }
}