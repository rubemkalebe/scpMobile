package imd.ufrn.br.scpmobile.model;

import java.util.Date;

/**
 * Created by f596024 on 07/12/2017.
 */

public class Mudanca {
    private Projeto projeto;
    private int id;
    private String descricao;
    private Date data_solitacao;
    private boolean aprovada;

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_solitacao() {
        return data_solitacao;
    }

    public void setData_solitacao(Date data_solitacao) {
        this.data_solitacao = data_solitacao;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }
}