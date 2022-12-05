package ui;

import java.awt.*;
import principal.Contexto;

public abstract class TelaBase extends Panel {
    public final static int RESULTADO_TELA_OK = 1;
    public final static int RESULTADO_TELA_CANCELADO = 2;

    private Contexto contexto;
    private boolean criada = false;
    private int resultado = 0;

    public TelaBase(Contexto contexto) {
        this.contexto = contexto;
        setLayout(new BorderLayout());
    }

    public void iniciarTela(TelaBase tela) {
        contexto.iniciarTela(tela);
    }

    public void iniciarTelaComResultado(TelaBase tela) {
        contexto.iniciarTelaComResultado(tela);
    }

    public void criar() {
        criada = true;
        aoCriar();
    }

    public abstract void aoCriar();

    public void resumir() {
        aoResumir();
    }

    public abstract void aoResumir();

    public void receberResultado(int resultado) {
        aoReceberResultado(resultado);
    }

    public abstract void aoReceberResultado(int resultado);

    public void finalizar() {
        contexto.removerTela();
    }

    public Contexto getContexto() {
        return contexto;
    }

    public boolean isCriada() {
        return criada;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getResultado() {
        return resultado;
    }

}