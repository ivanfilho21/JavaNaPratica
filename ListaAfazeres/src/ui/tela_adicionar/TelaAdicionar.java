package ui.tela_adicionar;

import db.BancoDeDados;
import java.awt.*;
import java.awt.event.*;
import model.Afazer;
import principal.Contexto;
import ui.TelaBase;

public class TelaAdicionar extends TelaBase {
    private Button btnSalvar = new Button("Salvar");
    private TextField tfTitulo = new TextField("");
    private TextField tfConteudo = new TextField("");

    public TelaAdicionar(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void aoCriar() {
        Button btnCancelar = new Button("Cancelar");
        
        Panel painelBaixo = new Panel(new GridLayout(4, 3, 0, 10));
        painelBaixo.add(new Label(""));
        painelBaixo.add(new Label(""));
        painelBaixo.add(new Label(""));

        painelBaixo.add(new Label(""));
        painelBaixo.add(btnSalvar);
        painelBaixo.add(new Label(""));

        painelBaixo.add(new Label(""));
        painelBaixo.add(btnCancelar);
        painelBaixo.add(new Label(""));

        painelBaixo.add(new Label(""));
        painelBaixo.add(new Label(""));
        painelBaixo.add(new Label(""));
        
        add(tfTitulo, BorderLayout.PAGE_START);
        add(tfConteudo, BorderLayout.CENTER);
        add(painelBaixo, BorderLayout.PAGE_END);

        btnSalvar.setEnabled(false);

        tfTitulo.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                validarBotao();
            }
        });

        tfConteudo.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                validarBotao();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarAnotacao();
                setResultado(TelaBase.RESULTADO_TELA_OK);
                finalizar();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
    }

    @Override
    public void aoResumir() {
        //
    }

    @Override
    public void aoReceberResultado(int resultado) {
        //
    }

    private void validarBotao() {
        boolean peloMenosUmCampoPreenchido = !tfTitulo.getText().trim().isEmpty() || !tfConteudo.getText().trim().isEmpty();
        boolean valido = peloMenosUmCampoPreenchido;
        btnSalvar.setEnabled(valido);
    }

    private void salvarAnotacao() {
        Afazer afazer = new Afazer();
        String titulo = tfTitulo.getText();
        String conteudo = tfConteudo.getText();
        afazer.setTitulo(titulo);
        afazer.setConteudo(conteudo);
        
        BancoDeDados.getInstancia().adicionarAfazer(afazer);
    }

}