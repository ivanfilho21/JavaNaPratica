package model;

import java.util.*;

public class Afazer {
    private int id;
    private String titulo;
    private String conteudo;
    private Date criadoEm;
    private Date atualizadoEm;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setAtualizadoEm(Date atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Date getAtualizadoEm() {
        return atualizadoEm;
    }

}